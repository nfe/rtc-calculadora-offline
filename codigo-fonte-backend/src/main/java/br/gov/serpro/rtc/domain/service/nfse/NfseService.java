/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.nfse;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.api.model.input.nfse.NfseBaseCalculoInput;
import br.gov.serpro.rtc.api.model.output.nfse.NfseBaseCalculoOutput;
import br.gov.serpro.rtc.domain.service.exception.CampoInvalidoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NfseService {
    
    private static final int ANO_INICIO_CBS_IBS = 2026;

    private static final int ANO_EXTINCAO_PIS_COFINS = 2027;
    private static final int ANO_EXTINCAO_ISS = 2033;
    
    private static final String MENSAGEM_ERRO_CBS_IBS = "O ano do fato gerador deve ser %d ou superior".formatted(ANO_INICIO_CBS_IBS);
    
    /**
     * Afere a base de cálculo para NFS-e, considerando os campos que integram e não
     * integram a base de cálculo
     * 
     * @param input
     * @return
     */
    public NfseBaseCalculoOutput calcularBaseCalculo(NfseBaseCalculoInput input) {
        
        final var anoFatoGerador = input.getAnoFatoGerador();
        validaAnoInicioTributo(anoFatoGerador, ANO_INICIO_CBS_IBS, MENSAGEM_ERRO_CBS_IBS);
        
        validaValoresForaLimiteVigenciaPermitido(Map.of(
                "PIS", input.getPis(),
                "COFINS", input.getCofins()), 
                anoFatoGerador, 
                ANO_EXTINCAO_PIS_COFINS);
        
        validaValoresForaLimiteVigenciaPermitido(Map.of(
                "ISS", input.getIss()), 
                anoFatoGerador, 
                ANO_EXTINCAO_ISS);
        
        BigDecimal integra = input.getValorServico();
        
        BigDecimal naoIntegra = input.getDescontoIncondicional()
                .add(input.getVCalcReeRepRes())
                .add(input.getVCalcDedRedIBSCBS())
                .add(input.getPis())
                .add(input.getCofins())
                .add(input.getIss());

        BigDecimal baseCalculo = integra.subtract(naoIntegra);
        
        validaValorFinalBaseCalculo(baseCalculo);

        return NfseBaseCalculoOutput.builder()
                .baseCalculo(baseCalculo)
                .build();
    }
    
    /**
     * Valida se o ano do fato gerador é inferior ao ano de início do tributo. Nesse
     * caso, lança uma exceção com a mensagem de erro informada.
     * 
     * @param anoFatoGerador
     * @param anoInicio
     * @param mensagemErro
     */
    protected void validaAnoInicioTributo(final Integer anoFatoGerador, final int anoInicio,
            final String mensagemErro) {
        if (anoFatoGerador < anoInicio) {
            throw new CampoInvalidoException(mensagemErro);
        }
    }
    
    /**
     * Valida se foram enviados os valores diferentes de zero para os campos
     * contidos no mapa caso o ano do fato gerador informado seja igual ou superior
     * ao ano de extinção informado.
     * 
     * @param campos
     * @param anoFatoGerador
     * @param anoExtincao
     */
    protected void validaValoresForaLimiteVigenciaPermitido(Map<String, BigDecimal> campos, int anoFatoGerador,
            int anoExtincao) {
        if (anoFatoGerador >= anoExtincao) {
            final String camposInformados = campos
                    .entrySet()
                    .stream()
                    .filter(entry -> ehDiferenteDeZero(entry.getValue()))
                    .map(Map.Entry::getKey)
                    .sorted()
                    .collect(Collectors.joining(", "));

            if (!camposInformados.isEmpty()) {
                throw new CampoInvalidoException("Os seguintes campos não podem ser informados a partir de %d: %s"
                        .formatted(anoExtincao, camposInformados));
            }
        }
    }
    
    /**
     * Valida se o valor final da base de cálculo é negativo. Caso seja, lança uma
     * exceção
     * 
     * @param baseCalculo
     */
    protected void validaValorFinalBaseCalculo(BigDecimal baseCalculo) {
        if (baseCalculo.compareTo(BigDecimal.ZERO) < 0) {
            throw new CampoInvalidoException(
                    "Base de cálculo não pode ser negativa. Valores que não integram a base são maiores que o valor do serviço.");
        }
    }
    
    protected boolean ehDiferenteDeZero(BigDecimal valor) {
        return valor != null && valor.compareTo(BigDecimal.ZERO) != 0;
    }

}