/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.calculotributo;

import static br.gov.serpro.rtc.domain.model.enumeration.TributoEnum.CBS;
import static br.gov.serpro.rtc.domain.model.enumeration.TributoEnum.IBS_ESTADUAL;
import static br.gov.serpro.rtc.domain.model.enumeration.TributoEnum.IBS_MUNICIPAL;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.api.model.input.ItemOperacaoInput;
import br.gov.serpro.rtc.api.model.output.CbsIbsOutput;
import br.gov.serpro.rtc.api.model.roc.AjusteCompetenciaDomain;
import br.gov.serpro.rtc.api.model.roc.CBSDomain;
import br.gov.serpro.rtc.api.model.roc.CreditoPresumidoIBSZFMDomain;
import br.gov.serpro.rtc.api.model.roc.CreditoPresumidoOperacaoDomain;
import br.gov.serpro.rtc.api.model.roc.DevolucaoTributosDomain;
import br.gov.serpro.rtc.api.model.roc.EstornoCreditoDomain;
import br.gov.serpro.rtc.api.model.roc.GrupoIBSCBSDomain;
import br.gov.serpro.rtc.api.model.roc.IBSCBSDomain;
import br.gov.serpro.rtc.api.model.roc.IBSMunDomain;
import br.gov.serpro.rtc.api.model.roc.IBSUFDomain;
import br.gov.serpro.rtc.api.model.roc.ImpostoSeletivoDomain;
import br.gov.serpro.rtc.api.model.roc.MonofasiaDiferimentoDomain;
import br.gov.serpro.rtc.api.model.roc.MonofasiaDomain;
import br.gov.serpro.rtc.api.model.roc.MonofasiaPadraoDomain;
import br.gov.serpro.rtc.api.model.roc.MonofasiaRetencaoDomain;
import br.gov.serpro.rtc.api.model.roc.MonofasiaRetidoAnteriormenteDomain;
import br.gov.serpro.rtc.api.model.roc.TransferenciaCreditoDomain;
import br.gov.serpro.rtc.api.model.roc.TributacaoCompraGovernamentalDomain;
import br.gov.serpro.rtc.api.model.roc.TributacaoRegularDomain;
import br.gov.serpro.rtc.api.model.roc.TributacaoRegularDomain.TributacaoRegularDomainBuilder;
import br.gov.serpro.rtc.api.model.roc.TributosDomain;
import br.gov.serpro.rtc.domain.model.dto.TratamentoClassificacaoDTO;
import br.gov.serpro.rtc.domain.model.enumeration.TributoEnum;
import br.gov.serpro.rtc.domain.service.MemoriaCalculoService;
import br.gov.serpro.rtc.domain.service.calculotributo.model.AliquotaImpostoSeletivoModel;
import br.gov.serpro.rtc.domain.service.calculotributo.model.OperacaoModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CalculoTributoService {

	private final CalculoCbsIbsService calculoCbsIbsService;
	private final CalculoImpostoSeletivoService calculoImpostoSeletivoService;
	private final MemoriaCalculoService memoriaCalculoService;

    @Value("${application.ibs.enabled}")
    private boolean calculoIbsHabilitado;

	public TributosDomain calcular(OperacaoModel operacao) {
		ItemOperacaoInput item = operacao.getItem();
		LocalDate data = operacao.getData();
		String nbs = item.getNbs();

		TratamentoClassificacaoDTO tratamentoClassificacaoCbsIbs = operacao
				.getTratamentoClassificacao().getTratamentoClassificacaoCbsIbs();
		TratamentoClassificacaoDTO tratamentoClassificacaoImpostoSeletivo = operacao
				.getTratamentoClassificacao().getTratamentoClassificacaoImpostoSeletivo();

		Boolean temDesoneracao = operacao.getTratamentoClassificacao().getTemDesoneracao();

		TratamentoClassificacaoDTO tratamentoClassificacaoCbsIbsDesoneracao = null;
		if (temDesoneracao) {
			tratamentoClassificacaoCbsIbsDesoneracao = operacao
				.getTratamentoClassificacao().getTratamentoClassificacaoCbsIbsDesoneracao();
		}
		
		TratamentoClassificacaoDTO tratamentoClassificacao = temDesoneracao ? tratamentoClassificacaoCbsIbsDesoneracao : tratamentoClassificacaoCbsIbs;

		ImpostoSeletivoDomain impostoSeletivo = null;
        if (tratamentoClassificacaoImpostoSeletivo != null) {
            AliquotaImpostoSeletivoModel aliquotaImpostoSeletivo = operacao.getTratamentoClassificacao()
                    .getAliquotaImpostoSeletivo();
            impostoSeletivo = calculoImpostoSeletivoService.calcularImpostoSeletivo(1L, item,
                    tratamentoClassificacaoImpostoSeletivo, aliquotaImpostoSeletivo, data);
            memoriaCalculoService.gerarMemoriaCalculoImpostoSeletivo(tratamentoClassificacaoImpostoSeletivo,
                    impostoSeletivo, item.getImpostoSeletivo().getQuantidade(), item.getImpostoSeletivo().getUnidade(),
                    data);
        }

        CbsIbsOutput cbs = null;
        CbsIbsOutput ibsEstadual = null;
        CbsIbsOutput ibsMunicipal = null;
        if (tratamentoClassificacaoCbsIbs != null) {
            final BigDecimal impostoSeletivoCalculado = impostoSeletivo != null
                    ? impostoSeletivo.getValorImpostoSeletivo()
                    : BigDecimal.ZERO;
            
            log.debug("Iniciando cálculo assíncrono de CBS/IBS...");
            final CompletableFuture<CbsIbsOutput> cbsFuture = calcularCbsIbsAsync(CBS, null, null, item,
                    tratamentoClassificacaoCbsIbs, impostoSeletivoCalculado, temDesoneracao, data,
                    tratamentoClassificacao, true);

            final var calcularIBS = calculoIbsHabilitado || nbs != null;

            final CompletableFuture<CbsIbsOutput> ibsEstadualFuture = calcularCbsIbsAsync(IBS_ESTADUAL,
                    operacao.getCodigoUf(), null, item, tratamentoClassificacaoCbsIbs, impostoSeletivoCalculado,
                    temDesoneracao, data, tratamentoClassificacao, calcularIBS);

            final CompletableFuture<CbsIbsOutput> ibsMunicipalFuture = calcularCbsIbsAsync(IBS_MUNICIPAL,
                    null, operacao.getCodigoMunicipio(), item, tratamentoClassificacaoCbsIbs, impostoSeletivoCalculado,
                    temDesoneracao, data, tratamentoClassificacao, calcularIBS);

            // Sincroniza e obtém os resultados
            try {
                log.debug("Aguardando cálculo assíncrono de CBS/IBS...");
                CompletableFuture
                    .allOf(cbsFuture, ibsEstadualFuture, ibsMunicipalFuture)
                    .join(); // aguarda a conclusão de todas as tarefas
                log.debug("Cálculo assíncrono de CBS/IBS concluído.");
                cbs = cbsFuture.get();
                ibsEstadual = ibsEstadualFuture.get();
                ibsMunicipal = ibsMunicipalFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Erro ao calcular CBS/IBS", e);
            }
        }

        if (temDesoneracao && impostoSeletivo != null) {
            impostoSeletivo.setVIS(BigDecimal.ZERO);
        }
		
		return TributosDomain
				.builder()
				.IS(impostoSeletivo)
				.IBSCBS(getIBSCBS(item, cbs, ibsEstadual, ibsMunicipal))
				.build();
	}
	
	/**
	 * Cálculo assíncrono de CBS/IBS
	 * @param tributo
	 * @param codigoUf
	 * @param codigoMunicipio
	 * @param item
	 * @param tratamentoClassificacaoCbsIbs
	 * @param impostoSeletivoCalculado
	 * @param temDesoneracao
	 * @param data
	 * @param tratamentoClassificacao
	 * @param calcularTributo
	 * @return
	 */
	private CompletableFuture<CbsIbsOutput> calcularCbsIbsAsync(
	        TributoEnum tributo,
	        Long codigoUf,
	        Long codigoMunicipio,
	        ItemOperacaoInput item,
	        TratamentoClassificacaoDTO tratamentoClassificacaoCbsIbs,
	        BigDecimal impostoSeletivoCalculado,
	        Boolean temDesoneracao,
	        LocalDate data,
	        TratamentoClassificacaoDTO tratamentoClassificacao,
	        boolean calcularTributo
	) {
	    return CompletableFuture.supplyAsync(() -> {
            final var descricao = tributo.getNome() + "(" + item.getNumero() + ")";
	        if (!calcularTributo) {
	            log.debug("> {} - Cálculo desabilitado", descricao);
	            return null;
	        }
	        log.debug("> {} - Iniciando cálculo assíncrono", descricao);
	        CbsIbsOutput output = calculoCbsIbsService.calcularCbsIbs(
	                tributo, codigoUf, codigoMunicipio, item,
	                tratamentoClassificacaoCbsIbs, impostoSeletivoCalculado, temDesoneracao, data);
	        log.debug("> {} - Cálculo assíncrono finalizado", descricao);
	        
	        log.debug(">> {} - Iniciando memória de cálculo", descricao);
	        memoriaCalculoService.gerarMemoriaCalculoCbsIbs(
	                tratamentoClassificacao, output, item.getQuantidade(), item.getUnidade(), data);
	        log.debug(">> {} - Memória de cálculo finalizada", descricao);
	        return output;
	    });
	}

    private static IBSCBSDomain getIBSCBS(ItemOperacaoInput item, CbsIbsOutput cbs, CbsIbsOutput ibsEstadual,
            CbsIbsOutput ibsMunicipal) {
        final var monofasia = getMonofasia(cbs, ibsEstadual, ibsMunicipal);
        return IBSCBSDomain.builder()
                .CST(item.getCst())
                .cClassTrib(item.getCClassTrib())
                .gIBSCBS(monofasia == null ? getGIBSCBS(cbs, ibsEstadual, ibsMunicipal) : null) // FIXME somente se não for monofásico
                .gIBSCBSMono(monofasia)
                .gTransfCred(getTransferenciaCredito(cbs, ibsEstadual, ibsMunicipal))
                .gAjusteCompet(getAjusteCompetencia(cbs, ibsEstadual, ibsMunicipal))
                .gEstornoCred(getEstornoCredito(cbs, ibsEstadual, ibsMunicipal))
                .gCredPresOper(getCreditoPresumidoOperacao(cbs, ibsEstadual, ibsMunicipal))
                .gCredPresIBSZFM(getCreditoPresumidoIBSZFM(ibsEstadual, ibsMunicipal))
                .build();
    }

    private static GrupoIBSCBSDomain getGIBSCBS(CbsIbsOutput cbs, CbsIbsOutput ibsEstadual, CbsIbsOutput ibsMunicipal) {
        final var vBC = getVBC(ibsMunicipal, getVBC(ibsEstadual, getVBC(cbs, null)));
        final var ibsUF = getIBSUF(ibsEstadual);
        final var ibsMun = getIBSMun(ibsMunicipal);
        final var vIBS = getVIbs(ibsUF, ibsMun);
        final var tributacaoRegular = getTributacaoRegular(cbs, ibsEstadual, ibsMunicipal);
        final var compraGovernamental = getCompraGovernamental(cbs, ibsEstadual, ibsMunicipal);
        return GrupoIBSCBSDomain.builder()
		        .vBC(vBC)
                .gIBSUF(ibsUF)
                .gIBSMun(ibsMun)
                .vIBS(vIBS)
		        .gCBS(getCbs(cbs))
		        .gTribRegular(tributacaoRegular)
		        .gTribCompraGov(compraGovernamental)
		        .build();
    }

    private static BigDecimal getVIbs(IBSUFDomain ibsUF, IBSMunDomain ibsMun) {
        final var vIbsUF = ibsUF != null ? ibsUF.getVIBSUF() : BigDecimal.ZERO;
        final var vIbsMun = ibsMun != null ? ibsMun.getVIBSMun() : BigDecimal.ZERO;
        return vIbsUF.add(vIbsMun);
    }
	
    private static BigDecimal getVBC(CbsIbsOutput c, BigDecimal valor) {
        if (valor != null) {
            return valor;
        }
        if (c != null) {
            return c.getBaseCalculo();
        }
        return null;
    }
	
    private static IBSUFDomain getIBSUF(CbsIbsOutput ibsEstadual) {
        if (ibsEstadual == null) {
            return null;
        }
        final IBSUFDomain ibsUF = new IBSUFDomain();
        ibsUF.setPIBSUF(ibsEstadual.getAliquota());
        ibsUF.setGDif(ibsEstadual.getGrupoDiferimento());
        ibsUF.setGDevTrib(getDevolucaoTributos(ibsEstadual));
        ibsUF.setGRed(ibsEstadual.getGrupoReducao());
        ibsUF.setVIBSUF(ibsEstadual.getTributoDevido());
        ibsUF.setMemoriaCalculo(ibsEstadual.getMemoriaCalculo());
        return ibsUF;
    }
	
	private static IBSMunDomain getIBSMun(CbsIbsOutput ibsMunicipal) {
	    if (ibsMunicipal == null) {
            return null;
        }
        final IBSMunDomain ibsMun = new IBSMunDomain();
        ibsMun.setPIBSMun(ibsMunicipal.getAliquota());
        ibsMun.setGDif(ibsMunicipal.getGrupoDiferimento());
        ibsMun.setGDevTrib(getDevolucaoTributos(ibsMunicipal));
        ibsMun.setGRed(ibsMunicipal.getGrupoReducao());
        ibsMun.setVIBSMun(ibsMunicipal.getTributoDevido());
        ibsMun.setMemoriaCalculo(ibsMunicipal.getMemoriaCalculo());
        return ibsMun;
	}

    private static CBSDomain getCbs(CbsIbsOutput cbsOut) {
        if (cbsOut == null) {
            return null;
        }
        final CBSDomain cbs = new CBSDomain();
        cbs.setPCBS(cbsOut.getAliquota());
        cbs.setGDif(cbsOut.getGrupoDiferimento());
        cbs.setGDevTrib(getDevolucaoTributos(cbsOut));
        cbs.setGRed(cbsOut.getGrupoReducao());
        cbs.setVCBS(cbsOut.getTributoDevido());
        cbs.setMemoriaCalculo(cbsOut.getMemoriaCalculo());
        return cbs;
    }
    
    // TODO: Implementar devolução de tributos
    private static DevolucaoTributosDomain getDevolucaoTributos(CbsIbsOutput d) {
        return null;
    }
    
    private static TributacaoRegularDomain getTributacaoRegular(CbsIbsOutput cbs, CbsIbsOutput ibsEstadual,
            CbsIbsOutput ibsMunicipal) {
        var builder = getTributacaoRegularCBS(cbs, null);
        builder = getTributacaoRegularIBSUF(ibsEstadual, builder);
        builder = getTributacaoRegularIBSMun(ibsMunicipal, builder);
        if (builder != null) {
            return builder.build();
        }
        return null;
    }
    
    private static TributacaoRegularDomainBuilder getTributacaoRegularIBSUF(CbsIbsOutput ibsUF, TributacaoRegularDomainBuilder builder) {
        if (ibsUF != null && ibsUF.getTributacaoRegular() != null) {
            var tr = ibsUF.getTributacaoRegular();
            if (builder == null) {
                builder = TributacaoRegularDomain.builder();
                builder.CSTReg(tr.getCst())
                    .cClassTribReg(tr.getCClassTrib());
            }
            builder.pAliqEfetRegIBSUF(tr.getAliquotaEfetiva())
                   .vTribRegIBSUF(tr.getTributoDevido());
            return builder;
        }
        return null;
    }
    
    private static TributacaoRegularDomainBuilder getTributacaoRegularIBSMun(CbsIbsOutput ibsMun, TributacaoRegularDomainBuilder builder) {
        if (ibsMun != null && ibsMun.getTributacaoRegular() != null) {
            var tr = ibsMun.getTributacaoRegular();
            if (builder == null) {
                builder = TributacaoRegularDomain.builder();
                builder.CSTReg(tr.getCst())
                    .cClassTribReg(tr.getCClassTrib());
            }
            builder.pAliqEfetRegIBSMun(tr.getAliquotaEfetiva())
                   .vTribRegIBSMun(tr.getTributoDevido());
            return builder;
        }
        return null;
    }
    
    private static TributacaoRegularDomainBuilder getTributacaoRegularCBS(CbsIbsOutput cbs, TributacaoRegularDomainBuilder builder) {
        if (cbs != null && cbs.getTributacaoRegular() != null) {
            var tr = cbs.getTributacaoRegular();
            if (builder == null) {
                builder = TributacaoRegularDomain.builder();
                builder.CSTReg(tr.getCst())
                    .cClassTribReg(tr.getCClassTrib());
            }
            builder.pAliqEfetRegCBS(tr.getAliquotaEfetiva())
                   .vTribRegCBS(tr.getTributoDevido());
            return builder;
        }
        return null;
    }
    
    // TODO - Implementar compra governamental
    private static TributacaoCompraGovernamentalDomain getCompraGovernamental(CbsIbsOutput cbs, 
            CbsIbsOutput ibsEstadual, CbsIbsOutput ibsMunicipal) {
        return null;        
    }
    
    // TODO - Implementar transferência de crédito
    private static TransferenciaCreditoDomain getTransferenciaCredito(CbsIbsOutput cbs, 
            CbsIbsOutput ibsEstadual, CbsIbsOutput ibsMunicipal) {
        return null;
    }
    
    // TODO - Implementar ajuste de competência
    private static AjusteCompetenciaDomain getAjusteCompetencia(CbsIbsOutput cbs, CbsIbsOutput ibsEstadual, 
            CbsIbsOutput ibsMunicipal) {
        return null;
    }
    
    // TODO - Implementar estorno de crédito
    private static EstornoCreditoDomain getEstornoCredito(CbsIbsOutput cbs, CbsIbsOutput ibsEstadual, 
            CbsIbsOutput ibsMunicipal) {
        return null;
    }
    
    // TODO - Implementar estorno de crédito
    private static CreditoPresumidoOperacaoDomain getCreditoPresumidoOperacao(CbsIbsOutput cbs, CbsIbsOutput ibsEstadual, 
            CbsIbsOutput ibsMunicipal) {
        return null;
    }
    
    // TODO - Implementar crédito presumido para IBS Zona Franca de Manaus
    private static CreditoPresumidoIBSZFMDomain getCreditoPresumidoIBSZFM(CbsIbsOutput ibsEstadual, 
            CbsIbsOutput ibsMunicipal) {
        return null;
    }
    
    private static MonofasiaDomain getMonofasia(CbsIbsOutput cbs, CbsIbsOutput ibsEstadual, CbsIbsOutput ibsMunicipal) {
        var monoPadrao = MonofasiaPadraoDomain.create(cbs, ibsEstadual, ibsMunicipal);
        var monoReten = MonofasiaRetencaoDomain.create(cbs, ibsEstadual, ibsMunicipal);
        var monoRet = MonofasiaRetidoAnteriormenteDomain.create(cbs, ibsEstadual, ibsMunicipal);
        var monoDif = MonofasiaDiferimentoDomain.create(cbs, ibsEstadual, ibsMunicipal);
        if (monoPadrao != null || monoReten != null || monoRet != null
                || monoDif != null) {
            /** 
             * Cálculo dos totalizadores conforme NT da NFe 1.30:
             * vTotIBSMonoItem = vIBSMono + vIBSMonoReten - vIBSMonoDif
             * vTotCBSMonoItem = vCBSMono + vCBSMonoReten - vCBSMonoDif
             */
            var monofasiaBuilder = MonofasiaDomain.builder()
                    .gMonoPadrao(monoPadrao)
                    .gMonoReten(monoReten)
                    .gMonoRet(monoRet)
                    .gMonoDif(monoDif);
            
            // Calcular totalizadores
            var vIBSMono = monoPadrao != null ? monoPadrao.getVIBSMono() : BigDecimal.ZERO;
            var vCBSMono = monoPadrao != null ? monoPadrao.getVCBSMono() : BigDecimal.ZERO;
            var vIBSMonoReten = monoReten != null ? monoReten.getVIBSMonoReten() : BigDecimal.ZERO;
            var vCBSMonoReten = monoReten != null ? monoReten.getVCBSMonoReten() : BigDecimal.ZERO;
            var vIBSMonoDif = monoDif != null ? monoDif.getVIBSMonoDif() : BigDecimal.ZERO;
            var vCBSMonoDif = monoDif != null ? monoDif.getVCBSMonoDif() : BigDecimal.ZERO;
            
            var vTotIBSMonoItem = vIBSMono.add(vIBSMonoReten).subtract(vIBSMonoDif);
            var vTotCBSMonoItem = vCBSMono.add(vCBSMonoReten).subtract(vCBSMonoDif);
            
            return monofasiaBuilder
                    .vTotIBSMonoItem(vTotIBSMonoItem)
                    .vTotCBSMonoItem(vTotCBSMonoItem)
                    .build();
        }
        return null;
    }

}