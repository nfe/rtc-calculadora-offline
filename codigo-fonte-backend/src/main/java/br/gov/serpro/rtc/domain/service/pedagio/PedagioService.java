/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.pedagio;

import static br.gov.serpro.rtc.api.model.output.pedagio.TotalPedagioOutput.getTotal;
import static br.gov.serpro.rtc.core.util.CalculadoraUtils.CEM;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.api.model.input.pedagio.PedagioInput;
import br.gov.serpro.rtc.api.model.output.pedagio.PedagioOutput;
import br.gov.serpro.rtc.api.model.output.pedagio.TrechoPedagioOutput;
import br.gov.serpro.rtc.api.model.output.pedagio.TributoPedagioOutput;
import br.gov.serpro.rtc.domain.model.enumeration.TipoWarningDadosSimulados;
import br.gov.serpro.rtc.domain.model.enumeration.TributoEnum;
import br.gov.serpro.rtc.domain.service.AliquotaPadraoService;
import br.gov.serpro.rtc.domain.service.UfService;
import br.gov.serpro.rtc.domain.service.exception.CampoInvalidoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PedagioService {

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

            Long codigoUf = ufService.buscar(trecho.getUf());

            BigDecimal valorAliquotaCbs = buscarAliquotaPadrao(TributoEnum.CBS, dataFatoGerador, null, null);
            BigDecimal valorAliquotaIbsEstadual = buscarAliquotaPadrao(TributoEnum.IBS_ESTADUAL, dataFatoGerador, codigoUf, null);
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
        final var aliquota = aliquotaPadraoService.buscarAliquota(tributo.getCodigo(), codigoUf, codigoMunicipio, data);
        return aliquota.getValorAplicavel().divide(CEM)
                .setScale(8, HALF_UP);
    }

    public TipoWarningDadosSimulados getWarningDadosSimulados(PedagioInput operacao) {
        LocalDate dataOperacao = operacao.getDataHoraEmissao().toLocalDate();
        LocalDate dataLimite = LocalDate.of(2027, 1, 1);
        
        if (dataOperacao.isBefore(dataLimite)) {
            return null;
        }
        
        return TipoWarningDadosSimulados.CASO_GERAL;
    }

}
