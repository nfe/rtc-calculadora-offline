/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.calculotributo;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.api.model.input.ItemOperacaoInput;
import br.gov.serpro.rtc.api.model.output.CbsIbsOutput;
import br.gov.serpro.rtc.api.model.output.GrupoDiferimentoMonofasiaOutput;
import br.gov.serpro.rtc.api.model.output.GrupoEtapaMonofasiaOutput;
import br.gov.serpro.rtc.api.model.output.GrupoMonofasiaOutput;
import br.gov.serpro.rtc.api.model.roc.CBS;
import br.gov.serpro.rtc.api.model.roc.TributacaoCompraGovernamental;
import br.gov.serpro.rtc.api.model.roc.CreditoPresumido;
import br.gov.serpro.rtc.api.model.roc.CreditoPresumidoIBSZFM;
import br.gov.serpro.rtc.api.model.roc.DevolucaoTributos;
import br.gov.serpro.rtc.api.model.roc.GrupoIBSCBS;
import br.gov.serpro.rtc.api.model.roc.IBSCBS;
import br.gov.serpro.rtc.api.model.roc.IBSMun;
import br.gov.serpro.rtc.api.model.roc.IBSUF;
import br.gov.serpro.rtc.api.model.roc.ImpostoSeletivo;
import br.gov.serpro.rtc.api.model.roc.Monofasia;
import br.gov.serpro.rtc.api.model.roc.TransferenciaCredito;
import br.gov.serpro.rtc.api.model.roc.TributacaoRegular;
import br.gov.serpro.rtc.api.model.roc.Tributos;
import br.gov.serpro.rtc.api.model.roc.Monofasia.MonofasiaBuilder;
import br.gov.serpro.rtc.api.model.roc.TributacaoRegular.TributacaoRegularBuilder;
import br.gov.serpro.rtc.domain.model.entity.TratamentoClassificacao;
import br.gov.serpro.rtc.domain.service.MemoriaCalculoService;
import br.gov.serpro.rtc.domain.service.calculotributo.model.AliquotaImpostoSeletivoModel;
import br.gov.serpro.rtc.domain.service.calculotributo.model.OperacaoModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CalculoTributoService {

	private final CalculoCbsIbsService calculoCbsIbsService;
	private final CalculoImpostoSeletivoService calculoImpostoSeletivoService;
	private final MemoriaCalculoService memoriaCalculoService;

    @Value("${application.ibs.enabled}")
    private boolean calculoIbsHabilitado;

	public Tributos calcular(OperacaoModel operacao) {
		ItemOperacaoInput item = operacao.getItem();
		LocalDate data = operacao.getData().toLocalDate();
		String ncm = item.getNcm();
		String nbs = item.getNbs();

		TratamentoClassificacao tratamentoClassificacaoCbsIbs = operacao
				.getTratamentoClassificacao().getTratamentoClassificacaoCbsIbs();
		TratamentoClassificacao tratamentoClassificacaoImpostoSeletivo = operacao
				.getTratamentoClassificacao().getTratamentoClassificacaoImpostoSeletivo();

		Boolean temDesoneracao = operacao.getTratamentoClassificacao().getTemDesoneracao();

		TratamentoClassificacao tratamentoClassificacaoCbsIbsDesoneracao = null;
		if (temDesoneracao) {
			tratamentoClassificacaoCbsIbsDesoneracao = operacao
				.getTratamentoClassificacao().getTratamentoClassificacaoCbsIbsDesoneracao();
		}

		ImpostoSeletivo impostoSeletivo = null;
		CbsIbsOutput cbs = null;
		CbsIbsOutput ibsEstadual = null;
		CbsIbsOutput ibsMunicipal = null;

		if (tratamentoClassificacaoImpostoSeletivo != null) {
			AliquotaImpostoSeletivoModel aliquotaImpostoSeletivo = operacao
					.getTratamentoClassificacao().getAliquotaImpostoSeletivo();

			impostoSeletivo = calculoImpostoSeletivoService.calcularImpostoSeletivo(1L, item,
					tratamentoClassificacaoImpostoSeletivo, aliquotaImpostoSeletivo, data);
		}

		if (tratamentoClassificacaoCbsIbs != null) {
			BigDecimal impostoSeletivoCalculado = impostoSeletivo != null
					? impostoSeletivo.getValorImpostoSeletivo()
					: BigDecimal.ZERO;
			cbs = calculoCbsIbsService.calcularCbsIbs(2L, operacao.getCodigoUf(), operacao.getCodigoMunicipio(), item, tratamentoClassificacaoCbsIbs,
					impostoSeletivoCalculado, temDesoneracao, data);
			if (calculoIbsHabilitado || nbs != null) {
				ibsEstadual = calculoCbsIbsService.calcularCbsIbs(3L, operacao.getCodigoUf(),
						operacao.getCodigoMunicipio(), item, tratamentoClassificacaoCbsIbs,
						impostoSeletivoCalculado, temDesoneracao, data);
				ibsMunicipal = calculoCbsIbsService.calcularCbsIbs(4L, operacao.getCodigoUf(),
						operacao.getCodigoMunicipio(), item, tratamentoClassificacaoCbsIbs,
						impostoSeletivoCalculado, temDesoneracao, data);
			}
		}

		if (temDesoneracao && impostoSeletivo != null) {
			impostoSeletivo.setVIS(BigDecimal.ZERO);
		}

		TratamentoClassificacao tratamentoClassificacao = null;

		if (temDesoneracao) {
			tratamentoClassificacao = tratamentoClassificacaoCbsIbsDesoneracao;
		} else {
			tratamentoClassificacao = tratamentoClassificacaoCbsIbs;
		}

		if (impostoSeletivo != null) {
		    String memoriaCalculoImpostoSeletivo = memoriaCalculoService
					.gerarMemoriaCalculoImpostoSeletivo(
						tratamentoClassificacaoImpostoSeletivo,
						impostoSeletivo,
						item.getImpostoSeletivo().getQuantidade(),
						item.getImpostoSeletivo().getUnidade(),
						data);
			impostoSeletivo.setMemoriaCalculo(memoriaCalculoImpostoSeletivo);
		}

        if (cbs != null) {
            String memoriaCalculoCbs = memoriaCalculoService.gerarMemoriaCalculoCbsIbs(tratamentoClassificacao, cbs,
                    item.getQuantidade(), item.getUnidade(), data);
            cbs.setMemoriaCalculo(memoriaCalculoCbs);
        }

		if (calculoIbsHabilitado || nbs != null) {

		    String memoriaCalculoIbsEstadual = memoriaCalculoService
					.gerarMemoriaCalculoCbsIbs(tratamentoClassificacao,
							ibsEstadual, item.getQuantidade(), item.getUnidade(), data);
			if (ibsEstadual != null) {
				ibsEstadual.setMemoriaCalculo(memoriaCalculoIbsEstadual);
			}

			String memoriaCalculoIbsMunicipal = memoriaCalculoService
					.gerarMemoriaCalculoCbsIbs(tratamentoClassificacao,
							ibsMunicipal, item.getQuantidade(), item.getUnidade(), data);
			if (ibsMunicipal != null) {
				ibsMunicipal.setMemoriaCalculo(memoriaCalculoIbsMunicipal);
			}
		}
		
		return Tributos
				.builder()
				.IS(impostoSeletivo)
				.IBSCBS(getIBSCBS(item, cbs, ibsEstadual, ibsMunicipal))
				.build();
	}

    private static IBSCBS getIBSCBS(ItemOperacaoInput item, CbsIbsOutput cbs, CbsIbsOutput ibsEstadual,
            CbsIbsOutput ibsMunicipal) {
        final var monofasia = getMonofasia(cbs, ibsEstadual, ibsMunicipal);
        return IBSCBS.builder()
                .CST(Integer.valueOf(item.getCst()))
                .cClassTrib(item.getCClassTrib())
                .gIBSCBS(monofasia == null ? getGIBSCBS(cbs, ibsEstadual, ibsMunicipal) : null) // FIXME somente se não for monofásico
                .gIBSCBSMono(monofasia)
                .gTransfCred(getTransferenciaCredito(cbs, ibsEstadual, ibsMunicipal))
                .gCredPresIBSZFM(getCreditoPresumidoIBSZFM(ibsEstadual, ibsMunicipal))
                .build();
    }

    private static GrupoIBSCBS getGIBSCBS(CbsIbsOutput cbs, CbsIbsOutput ibsEstadual, CbsIbsOutput ibsMunicipal) {
        final var vBC = getVBC(ibsMunicipal, getVBC(ibsEstadual, getVBC(cbs, null)));
        final var ibsUF = getIBSUF(ibsEstadual);
        final var ibsMun = getIBSMun(ibsMunicipal);
        final var tributacaoRegular = getTributacaoRegular(cbs, ibsEstadual, ibsMunicipal);
        final var creditoPresumidoIBS = getCreditoPresumidoIBS(ibsEstadual, ibsMunicipal);
        final var creditoPresumidoCBS = getCreditoPresumidoCBS(cbs);
        final var compraGovernamental = getCompraGovernamental(cbs, ibsEstadual, ibsMunicipal);
        return GrupoIBSCBS.builder()
		        .vBC(vBC)
                .gIBSUF(ibsUF)
                .gIBSMun(ibsMun)
		        .gCBS(getCbs(cbs))
		        .gTribRegular(tributacaoRegular)
		        .gIBSCredPres(creditoPresumidoIBS)
		        .gCBSCredPres(creditoPresumidoCBS)
		        .gTribCompraGov(compraGovernamental)
		        .build();
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
	
    private static IBSUF getIBSUF(CbsIbsOutput ibsEstadual) {
        if (ibsEstadual == null) {
            return null;
        }
        final IBSUF ibsUF = new IBSUF();
        ibsUF.setAliquota(ibsEstadual.getAliquota());
        ibsUF.setGDif(ibsEstadual.getGrupoDiferimento());
        ibsUF.setGDevTrib(getDevolucaoTributos(ibsEstadual));
        ibsUF.setGRed(ibsEstadual.getGrupoReducao());
        ibsUF.setValorImposto(ibsEstadual.getTributoDevido());
        ibsUF.setMemoriaCalculo(ibsEstadual.getMemoriaCalculo());
        return ibsUF;
    }
	
	private static IBSMun getIBSMun(CbsIbsOutput ibsMunicipal) {
	    if (ibsMunicipal == null) {
            return null;
        }
        final IBSMun ibsMun = new IBSMun();
        ibsMun.setAliquota(ibsMunicipal.getAliquota());
        ibsMun.setGDif(ibsMunicipal.getGrupoDiferimento());
        ibsMun.setGDevTrib(getDevolucaoTributos(ibsMunicipal));
        ibsMun.setGRed(ibsMunicipal.getGrupoReducao());
        ibsMun.setValorImposto(ibsMunicipal.getTributoDevido());
        ibsMun.setMemoriaCalculo(ibsMunicipal.getMemoriaCalculo());
        return ibsMun;
	}

    private static CBS getCbs(CbsIbsOutput cbsOut) {
        if (cbsOut == null) {
            return null;
        }
        final CBS cbs = new CBS();
        cbs.setAliquota(cbsOut.getAliquota());
        cbs.setGDif(cbsOut.getGrupoDiferimento());
        cbs.setGDevTrib(getDevolucaoTributos(cbsOut));
        cbs.setGRed(cbsOut.getGrupoReducao());
        cbs.setValorImposto(cbsOut.getTributoDevido());
        cbs.setMemoriaCalculo(cbsOut.getMemoriaCalculo());
        return cbs;
    }
    
    // TODO: Implementar devolução de tributos
    private static DevolucaoTributos getDevolucaoTributos(CbsIbsOutput d) {
        return null;
    }
    
    private static TributacaoRegular getTributacaoRegular(CbsIbsOutput cbs, CbsIbsOutput ibsEstadual,
            CbsIbsOutput ibsMunicipal) {
        var builder = getTributacaoRegularCBS(cbs, null);
        builder = getTributacaoRegularIBSUF(ibsEstadual, builder);
        builder = getTributacaoRegularIBSMun(ibsMunicipal, builder);
        if (builder != null) {
            return builder.build();
        }
        return null;
    }
    
    private static TributacaoRegularBuilder getTributacaoRegularIBSUF(CbsIbsOutput ibsUF, TributacaoRegularBuilder builder) {
        if (ibsUF != null && ibsUF.getTributacaoRegular() != null) {
            var tr = ibsUF.getTributacaoRegular();
            if (builder == null) {
                builder = TributacaoRegular.builder();
                builder.CSTReg(Integer.valueOf(tr.getCst()))
                    .cClassTribReg(tr.getCClassTrib());
            }
            builder.pAliqEfetRegIBSUF(tr.getAliquotaEfetiva())
                   .vTribRegIBSUF(tr.getTributoDevido());
            return builder;
        }
        return null;
    }
    
    private static TributacaoRegularBuilder getTributacaoRegularIBSMun(CbsIbsOutput ibsMun, TributacaoRegularBuilder builder) {
        if (ibsMun != null && ibsMun.getTributacaoRegular() != null) {
            var tr = ibsMun.getTributacaoRegular();
            if (builder == null) {
                builder = TributacaoRegular.builder();
                builder.CSTReg(Integer.valueOf(tr.getCst()))
                    .cClassTribReg(tr.getCClassTrib());
            }
            builder.pAliqEfetRegIBSMun(tr.getAliquotaEfetiva())
                   .vTribRegIBSMun(tr.getTributoDevido());
            return builder;
        }
        return null;
    }
    
    private static TributacaoRegularBuilder getTributacaoRegularCBS(CbsIbsOutput cbs, TributacaoRegularBuilder builder) {
        if (cbs != null && cbs.getTributacaoRegular() != null) {
            var tr = cbs.getTributacaoRegular();
            if (builder == null) {
                builder = TributacaoRegular.builder();
                builder.CSTReg(Integer.valueOf(tr.getCst()))
                    .cClassTribReg(tr.getCClassTrib());
            }
            builder.pAliqEfetRegCBS(tr.getAliquotaEfetiva())
                   .vTribRegCBS(tr.getTributoDevido());
            return builder;
        }
        return null;
    }
    
    // TODO - Implementar crédito presumido para IBS
    private static CreditoPresumido getCreditoPresumidoIBS(CbsIbsOutput ibsEstadual, CbsIbsOutput ibsMunicipal) {
        return null;
    }

    // TODO - Implementar crédito presumido para CBS
    private static CreditoPresumido getCreditoPresumidoCBS(CbsIbsOutput cbs) {
        return null;
    }

    // TODO - Implementar compra governamental
    private static TributacaoCompraGovernamental getCompraGovernamental(CbsIbsOutput cbs, 
            CbsIbsOutput ibsEstadual, CbsIbsOutput ibsMunicipal) {
        return null;        
    }
    
    // TODO - Implementar transferência de crédito
    private static TransferenciaCredito getTransferenciaCredito(CbsIbsOutput cbs, 
            CbsIbsOutput ibsEstadual, CbsIbsOutput ibsMunicipal) {
        return null;
    }
    
    // TODO - Implementar crédito presumido para IBS Zona Franca de Manaus
    private static CreditoPresumidoIBSZFM getCreditoPresumidoIBSZFM(CbsIbsOutput ibsEstadual, 
            CbsIbsOutput ibsMunicipal) {
        return null;
    }
    
    private static Monofasia getMonofasia(CbsIbsOutput cbs, CbsIbsOutput ibsEstadual, CbsIbsOutput ibsMunicipal) {
        var builder = getMonofasiaCBS(cbs, null);
        builder = getMonofasiaIBS(ibsEstadual, ibsMunicipal, builder);
        if (builder != null) {
            return builder.build();
        }
        return null;
    }
    
    private static MonofasiaBuilder getMonofasiaCBS(CbsIbsOutput cbs, MonofasiaBuilder builder) {
        if (cbs != null && cbs.getGrupoMonofasia() != null) {
            if (builder == null) {
                builder = Monofasia.builder();
            }
            var mono = cbs.getGrupoMonofasia();
            
            var tributoMonofasico = mono.getTributoMonofasico();
            if (tributoMonofasico != null) {
                builder.qBCMono(tributoMonofasico.getQuantidade());
                builder.adRemCBS(tributoMonofasico.getAliquotaAdRem());
                builder.vCBSMono(tributoMonofasico.getValor());
            }
            
            var tributoRetido = mono.getTributoRetido();
            if (tributoRetido != null) {
                builder.qBCMonoRet(tributoRetido.getQuantidade());
                builder.adRemCBSRet(tributoRetido.getAliquotaAdRem());
                builder.vCBSMonoRet(tributoRetido.getValor());
            }
            
            var tributoSujeitoRetencao = mono.getTributoSujeitoRetencao();
            if (tributoSujeitoRetencao != null) {
                builder.qBCMonoReten(tributoSujeitoRetencao.getQuantidade());
                builder.adRemCBSReten(tributoSujeitoRetencao.getAliquotaAdRem());
                builder.vCBSMonoReten(tributoSujeitoRetencao.getValor());
            }

            var tributoDiferido = mono.getTributoDiferido();
            if (tributoDiferido != null) {
                builder.pDifCBS(tributoDiferido.getPercentualDiferimento());
                builder.vCBSMonoDif(tributoDiferido.getValorDiferimento());
            }
            
            /** 
             * FIXME aqui falta totalizar - nao esta especificado como fazer isso.
             * Na NT da NFe, consta:
             * 
             * vCBSMono + vCBSMonoReten - vCBSMonoDif
             *  
             * builder.vTotCBSMonoItem(vCBSMono + vCBSMonoReten - vCBSMonoDif);
             */
            return builder;
        }
        return null;
    }
    
    private static MonofasiaBuilder getMonofasiaIBS(CbsIbsOutput ibsUF, CbsIbsOutput ibsMun, MonofasiaBuilder builder) {
        var monoUF = ibsUF != null ? ibsUF.getGrupoMonofasia() : null;
        var monoMun = ibsMun != null ? ibsMun.getGrupoMonofasia() : null;
        if (monoUF != null || monoMun != null) {
            if (builder == null) {
                builder = Monofasia.builder();
            }
            
            // pra garantir que não seja nulo
            monoUF = monoUF != null ? monoUF : GrupoMonofasiaOutput.builder().build();
            monoMun = monoMun != null ? monoMun : GrupoMonofasiaOutput.builder().build();

            var tributoMonofasico = GrupoEtapaMonofasiaOutput.merge(monoUF.getTributoMonofasico(), monoMun.getTributoMonofasico());
            if (tributoMonofasico != null) {
                builder.qBCMono(tributoMonofasico.getQuantidade());
                builder.adRemIBS(tributoMonofasico.getAliquotaAdRem());
                builder.vIBSMono(tributoMonofasico.getValor());
            }
            
            var tributoRetido = GrupoEtapaMonofasiaOutput.merge(monoUF.getTributoRetido(), monoMun.getTributoRetido());
            if (tributoRetido != null) {
                builder.qBCMonoRet(tributoRetido.getQuantidade());
                builder.adRemIBSRet(tributoRetido.getAliquotaAdRem());
                builder.vIBSMonoRet(tributoRetido.getValor());
            }
            
            var tributoSujeitoRetencao = GrupoEtapaMonofasiaOutput.merge(monoUF.getTributoSujeitoRetencao(), monoMun.getTributoSujeitoRetencao());
            if (tributoSujeitoRetencao != null) {
                builder.qBCMonoReten(tributoSujeitoRetencao.getQuantidade());
                builder.adRemIBSReten(tributoSujeitoRetencao.getAliquotaAdRem());
                builder.vIBSMonoReten(tributoSujeitoRetencao.getValor());
            }
            
            var tributoDiferido = GrupoDiferimentoMonofasiaOutput.merge(monoUF.getTributoDiferido(), monoMun.getTributoDiferido());
            if (tributoDiferido != null) {
                builder.pDifIBS(tributoDiferido.getPercentualDiferimento());
                builder.vIBSMonoDif(tributoDiferido.getValorDiferimento());
            }
            
            /** 
             * FIXME aqui falta totalizar - nao esta especificado como fazer isso.
             * Na NT da NFe, consta:
             * 
             * vIBSMono + vIBSMonoReten - vIBSMonoDif 
             * 
             * builder.vTotIBSMonoItem(vIBSMono + vIBSMonoReten - vIBSMonoDif);
             */
            
            return builder;
        }

        return null;
    }
}