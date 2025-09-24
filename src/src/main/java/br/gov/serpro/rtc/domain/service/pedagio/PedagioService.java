/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.pedagio;

import static br.gov.serpro.rtc.api.model.output.pedagio.TotalPedagioOutput.getTotal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.api.model.input.pedagio.PedagioInput;
import br.gov.serpro.rtc.api.model.output.pedagio.PedagioOutput;
import br.gov.serpro.rtc.api.model.output.pedagio.TrechoPedagioOutput;
import br.gov.serpro.rtc.api.model.output.pedagio.TributoPedagioOutput;
import br.gov.serpro.rtc.core.util.CalculadoraUtils;
import br.gov.serpro.rtc.domain.model.entity.AliquotaPadrao;
import br.gov.serpro.rtc.domain.model.entity.AliquotaReferencia;
import br.gov.serpro.rtc.domain.model.entity.Uf;
import br.gov.serpro.rtc.domain.model.enumeration.TributoEnum;
import br.gov.serpro.rtc.domain.service.AliquotaPadraoService;
import br.gov.serpro.rtc.domain.service.AliquotaReferenciaService;
import br.gov.serpro.rtc.domain.service.UfService;
import br.gov.serpro.rtc.domain.service.exception.CampoInvalidoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PedagioService {

    private static final DateTimeFormatter FORMATO_DATA_HORA = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final String NUMERO_VERSAO = "1.0.0(" + LocalDateTime.now().format(FORMATO_DATA_HORA) + ")";

    private final AliquotaReferenciaService aliquotaReferenciaService;
    private final AliquotaPadraoService aliquotaPadraoService;

    private final UfService ufService;

    public PedagioOutput calcularCIBS(PedagioInput operacao) {

        final List<TrechoPedagioOutput> trechosPedagio = getTrechosPedagio(operacao);
        return PedagioOutput.builder()
                .cst(operacao.getCst())
                .cClassTrib(operacao.getCClassTrib())
                .extensaoTotal(operacao.getExtensaoTotal())
                .dataHoraEmissao(operacao.getDataHoraEmissao())
                .municipioOrigem(operacao.getCodigoMunicipioOrigem())
                .baseCalculo(operacao.getBaseCalculo())
                .ufMunicipioOrigem(operacao.getUfMunicipioOrigem())
                .trechos(trechosPedagio)
                .total(getTotal(trechosPedagio))
                .build();
    }

    private List<TrechoPedagioOutput> getTrechosPedagio(PedagioInput operacao) {

        final BigDecimal x = operacao.getExtensaoTotal();

        return operacao.getTrechos().parallelStream().map(trecho -> {

            final BigDecimal baseCalculoCIBS = operacao.getBaseCalculo();

            final LocalDate dataFatoGerador = operacao.getDataHoraEmissao().toLocalDate();

            Uf uf = ufService.buscar(trecho.getUf());

            BigDecimal valorAliquotaCbs = buscarAliquotaPadrao(TributoEnum.CBS, dataFatoGerador, null, null);
            BigDecimal valorAliquotaIbsEstadual = buscarAliquotaPadrao(TributoEnum.IBS_ESTADUAL, dataFatoGerador, uf.getCodigo(), null);
            BigDecimal valorAliquotaIbsMunicipal = buscarAliquotaPadrao(TributoEnum.IBS_MUNICIPAL, dataFatoGerador, null, trecho.getMunicipio());

            if (valorAliquotaCbs == null || valorAliquotaIbsEstadual == null || valorAliquotaIbsMunicipal == null) {
                throw new CampoInvalidoException("Erro ao obter alíquotas");
            }

            BigDecimal valorAliquotaEfetivaCbs = valorAliquotaCbs
                    .multiply(trecho.getExtensao()).divide(x, 8, RoundingMode.DOWN);
            BigDecimal valorAliquotaEfetivaIbsEstadual = valorAliquotaIbsEstadual
                    .multiply(trecho.getExtensao()).divide(x, 8, RoundingMode.DOWN);
            BigDecimal valorAliquotaEfetivaIbsMunicipal = valorAliquotaIbsMunicipal
                    .multiply(trecho.getExtensao()).divide(x, 8, RoundingMode.DOWN);

            final TributoPedagioOutput cbs = obterCIBS(valorAliquotaCbs, valorAliquotaEfetivaCbs, baseCalculoCIBS);
            final TributoPedagioOutput ibsEstado = obterCIBS(valorAliquotaIbsEstadual, valorAliquotaEfetivaIbsEstadual,
                    baseCalculoCIBS);
            final TributoPedagioOutput ibsMunicipio = obterCIBS(valorAliquotaIbsMunicipal,
                    valorAliquotaEfetivaIbsMunicipal, baseCalculoCIBS);

            return TrechoPedagioOutput
                    .builder()
                    .numero(trecho.getNumero())
                    .uf(trecho.getUf())
                    .municipio(trecho.getMunicipio())
                    .baseCalculo(baseCalculoCIBS)
                    .cbs(cbs)
                    .ibsEstadual(ibsEstado)
                    .ibsMunicipal(ibsMunicipio)
                    .extensaoTrecho(trecho.getExtensao())
                    .build();
        }).sorted().toList();
    }

    private static TributoPedagioOutput obterCIBS(
            BigDecimal aliquota,
            BigDecimal aliquotaEfetiva,
            BigDecimal baseCalculo) {
        return TributoPedagioOutput
                .builder()
                .aliquota(aliquota)
                .aliquotaEfetiva(aliquotaEfetiva)
                .tributoCalculado(calcularTributo(baseCalculo, aliquotaEfetiva))
                .build();
    }

    private static BigDecimal calcularTributo(BigDecimal baseCalculo, BigDecimal aliquota) {
        return baseCalculo.multiply(aliquota).setScale(4, RoundingMode.HALF_UP);
    }

    public BigDecimal buscarAliquotaPadrao(TributoEnum tributo, LocalDate data, Long codigoUf, Long codigoMunicipio) {
        AliquotaReferencia aliquotaReferencia = aliquotaReferenciaService.buscar(tributo.getCodigo(), data);
        AliquotaPadrao aliquotaPadrao = null;

        if (tributo == TributoEnum.CBS) {
            aliquotaPadrao = aliquotaPadraoService.buscarAliquotaUniao(aliquotaReferencia, data);
        } else if (tributo == TributoEnum.IBS_ESTADUAL) {
            aliquotaPadrao = aliquotaPadraoService.buscarAliquotaUf(aliquotaReferencia, codigoUf, data);
        } else if (tributo == TributoEnum.IBS_MUNICIPAL) {
            aliquotaPadrao = aliquotaPadraoService.buscarAliquotaMunicipio(aliquotaReferencia, codigoMunicipio, data);
        }
        
        if (aliquotaPadrao != null) {
            return aliquotaPadrao
                    .getValorAplicavel()
                    .divide(CalculadoraUtils.CEM)
                    .setScale(8, RoundingMode.HALF_UP);
        }

        return aliquotaReferenciaService
                .buscar(tributo.getCodigo(), data)
                .getValor()
                .divide(CalculadoraUtils.CEM)
                .setScale(8, RoundingMode.HALF_UP);
    }

}
