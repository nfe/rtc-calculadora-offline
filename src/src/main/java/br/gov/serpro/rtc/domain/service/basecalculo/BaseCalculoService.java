/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.basecalculo;

import br.gov.serpro.rtc.api.model.input.basecalculo.BaseCalculoCibsInput;
import br.gov.serpro.rtc.api.model.input.basecalculo.BaseCalculoISMercadoriasInput;
import br.gov.serpro.rtc.api.model.output.basecalculo.BaseCalculoCibsModel;
import br.gov.serpro.rtc.api.model.output.basecalculo.BaseCalculoISMercadoriasModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class BaseCalculoService {

    public BaseCalculoISMercadoriasModel calcularISMercadorias(BaseCalculoISMercadoriasInput input) {

        BigDecimal integra = input.getValorIntegralCobrado()
                .add(input.getAjusteValorOperacao())
                .add(input.getJuros())
                .add(input.getMultas())
                .add(input.getAcrescimos())
                .add(input.getEncargos())
                .add(input.getDescontosCondicionais())
                .add(input.getFretePorDentro())
                .add(input.getOutrosTributos())
                .add(input.getDemaisImportancias());

        BigDecimal naoIntegra = input.getIcms()
                .add(input.getIss())
                .add(input.getPis())
                .add(input.getCofins())
                .add(input.getBonificacao())
                .add(input.getDevolucaoVendas());

        return BaseCalculoISMercadoriasModel.builder()
                .baseCalculo(integra.subtract(naoIntegra))
                .build();
    }

    public BaseCalculoCibsModel calcularCibs(BaseCalculoCibsInput input) {

        BigDecimal integra = input.getValorFornecimento()
                .add(input.getAjusteValorOperacao())
                .add(input.getJuros())
                .add(input.getMultas())
                .add(input.getAcrescimos())
                .add(input.getEncargos())
                .add(input.getDescontosCondicionais())
                .add(input.getFretePorDentro())
                .add(input.getOutrosTributos())
                .add(input.getImpostoSeletivo())
                .add(input.getDemaisImportancias());

        BigDecimal naoIntegra = input.getIcms()
                .add(input.getIss())
                .add(input.getPis())
                .add(input.getCofins());

        return BaseCalculoCibsModel.builder()
                .baseCalculo(integra.subtract(naoIntegra))
                .build();
    }
}