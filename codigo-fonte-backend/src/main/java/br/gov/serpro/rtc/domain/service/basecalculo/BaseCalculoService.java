/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.basecalculo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.api.model.input.basecalculo.BaseCalculoCibsInput;
import br.gov.serpro.rtc.api.model.input.basecalculo.BaseCalculoISMercadoriasInput;
import br.gov.serpro.rtc.api.model.output.basecalculo.BaseCalculoCibsModel;
import br.gov.serpro.rtc.api.model.output.basecalculo.BaseCalculoISMercadoriasModel;
import br.gov.serpro.rtc.domain.service.exception.CampoInvalidoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BaseCalculoService {

    private static final int ANO_INICIO_CBS_IBS = 2026;
    private static final int ANO_INICIO_IS = 2027;
    
    private static final int ANO_EXTINCAO_PIS_COFINS = 2027;
    private static final int ANO_EXTINCAO_ICMS_ISS = 2033;
    
    private static final String MENSAGEM_ERRO_IS = "Para IS, o ano do fato gerador deve ser %d ou superior".formatted(ANO_INICIO_IS);
    private static final String MENSAGEM_ERRO_CBS_IBS = "Para CBS/IBS, o ano do fato gerador deve ser %d ou superior".formatted(ANO_INICIO_CBS_IBS);
    
    /**
     * Afere a base de cálculo para IS sobre mercadorias, considerando os campos que
     * integram e não integram a base de cálculo conforme as regras do tributo.
     * 
     * @param input
     * @return
     */
    public BaseCalculoISMercadoriasModel calcularISMercadorias(BaseCalculoISMercadoriasInput input) {

        final var anoFatoGerador = input.getAnoFatoGerador();
        validaAnoInicioTributo(anoFatoGerador, ANO_INICIO_IS, MENSAGEM_ERRO_IS);
        
        validaValoresForaLimiteVigenciaPermitido(Map.of(
                "ICMS", input.getIcms(),
                "ISS", input.getIss()), 
                anoFatoGerador, 
                ANO_EXTINCAO_ICMS_ISS);
        
        final BigDecimal integra = input.getValorBem()
                .add(input.getAjusteAcrescimos())
                .add(input.getJuros())
                .add(input.getMultas())
                .add(input.getEncargos())
                .add(input.getFreteCobrado())
                .add(input.getOutrosTributos())
                .add(input.getDemaisImportancias());

        final BigDecimal naoIntegra = input.getCosip()
                .add(input.getIpi())
                .add(input.getDescontoIncondicional())
                .add(input.getBonificacao())
                .add(input.getDevolucaoVendas())
                .add(input.getIcms())
                .add(input.getIss());

        final BigDecimal baseCalculo = integra.subtract(naoIntegra);
        
        validaValorFinalBaseCalculo(baseCalculo);

        return BaseCalculoISMercadoriasModel.builder()
                .baseCalculo(baseCalculo)
                .build();
    }

    /**
     * Afere a base de cálculo para CBS/IBS, considerando os campos que integram e
     * não integram a base de cálculo conforme as regras de cada tributo.
     * 
     * @param input
     * @return
     */
    public BaseCalculoCibsModel calcularCibs(BaseCalculoCibsInput input) {
        final var anoFatoGerador = input.getAnoFatoGerador();
        
        validaAnoInicioTributo(anoFatoGerador, ANO_INICIO_CBS_IBS, MENSAGEM_ERRO_CBS_IBS);
        
        validaValoresForaLimiteVigenciaPermitido(Map.of(
                "PIS", input.getPis(),
                "PIS Importação", input.getPisImportacao(),
                "COFINS", input.getCofins(),
                "COFINS Importação", input.getCofinsImportacao()), 
                anoFatoGerador, 
                ANO_EXTINCAO_PIS_COFINS);
        
        validaValoresForaLimiteVigenciaPermitido(Map.of(
                "ICMS", input.getIcms(),
                "ISS", input.getIss()), 
                anoFatoGerador, 
                ANO_EXTINCAO_ICMS_ISS);
       
        final BigDecimal integra = input.getValorBem()
                .add(input.getAjusteAcrescimos())
                .add(input.getJuros())
                .add(input.getMultas())
                .add(input.getEncargos())
                .add(input.getFrete())
                .add(input.getImpostoSeletivo())
                .add(input.getOutrosTributos())
                .add(input.getDemaisImportancias());

        final BigDecimal naoIntegra = input.getCosip()
                .add(input.getIpi())
                .add(input.getDescontoIncondicional())
                .add(input.getPis())
                .add(input.getPisImportacao())
                .add(input.getCofins())
                .add(input.getCofinsImportacao())
                .add(input.getIcms())
                .add(input.getIss());

        final BigDecimal baseCalculo = integra.subtract(naoIntegra);
        
        validaValorFinalBaseCalculo(baseCalculo);

        return BaseCalculoCibsModel.builder()
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
                    "Base de cálculo não pode ser negativa. A soma dos valores que não integram a base é maior que a soma dos valores que integram a base.");
        }
    }
    
    protected boolean ehDiferenteDeZero(BigDecimal valor) {
        return valor != null && valor.compareTo(BigDecimal.ZERO) != 0;
    }
}