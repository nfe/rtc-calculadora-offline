package br.gov.serpro.rtc.domain.service;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.gov.serpro.rtc.api.model.input.ItemOperacaoInput;
import br.gov.serpro.rtc.domain.service.calculotributo.model.AliquotaImpostoSeletivoModel;
import br.gov.serpro.rtc.domain.service.exception.ErroGenericoValidacaoException;

class CalculadoraServiceTest {

    private static CalculadoraService service;

    @BeforeAll
    static void setUp() {
        // No momento não estamos testando dependências, então podem ser nulas
        service = new CalculadoraService(null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    @Test
    void deveLancarErroQuandoQuantidadeNaoInformada() {
        ItemOperacaoInput item = new ItemOperacaoInput();
        item.setQuantidade(null);
        item.setUnidade("UN");

        AliquotaImpostoSeletivoModel aliquota = AliquotaImpostoSeletivoModel.builder()
                .aliquotaAdRem(BigDecimal.ONE)
                .unidadeMedida("UN")
                .build();

        assertThatThrownBy(() -> service.validarQuantidadeEUnidade(item, aliquota))
                .isInstanceOf(ErroGenericoValidacaoException.class)
                .hasMessageContaining("A quantidade não foi informada.");
    }

    @Test
    void deveLancarErroQuandoQuantidadeIgualAZero() {
        ItemOperacaoInput item = new ItemOperacaoInput();
        item.setQuantidade(BigDecimal.ZERO);
        item.setUnidade("UN");

        AliquotaImpostoSeletivoModel aliquota = AliquotaImpostoSeletivoModel.builder()
                .aliquotaAdRem(BigDecimal.ONE)
                .unidadeMedida("UN")
                .build();

        assertThatThrownBy(() -> service.validarQuantidadeEUnidade(item, aliquota))
                .isInstanceOf(ErroGenericoValidacaoException.class)
                .hasMessageContaining("A quantidade deve ser maior do que zero.");
    }
    
    @Test
    void deveLancarErroQuandoQuantidadeMenorQueZero() {
        ItemOperacaoInput item = new ItemOperacaoInput();
        item.setQuantidade(new BigDecimal("-1"));
        item.setUnidade("UN");

        AliquotaImpostoSeletivoModel aliquota = AliquotaImpostoSeletivoModel.builder()
                .aliquotaAdRem(BigDecimal.ONE)
                .unidadeMedida("UN")
                .build();

        assertThatThrownBy(() -> service.validarQuantidadeEUnidade(item, aliquota))
                .isInstanceOf(ErroGenericoValidacaoException.class)
                .hasMessageContaining("A quantidade deve ser maior do que zero.");
    }

    @Test
    void deveLancarErroQuandoUnidadeNaoInformada() {
        ItemOperacaoInput item = new ItemOperacaoInput();
        item.setQuantidade(BigDecimal.ONE);
        item.setUnidade(null);

        AliquotaImpostoSeletivoModel aliquota = AliquotaImpostoSeletivoModel.builder()
                .aliquotaAdRem(BigDecimal.ONE)
                .unidadeMedida("UN")
                .build();

        assertThatThrownBy(() -> service.validarQuantidadeEUnidade(item, aliquota))
                .isInstanceOf(ErroGenericoValidacaoException.class)
                .hasMessageContaining("A unidade de medida do item não foi informada.");
    }

    @Test
    void deveLancarErroQuandoUnidadeDiferenteDaAliquota() {
        ItemOperacaoInput item = new ItemOperacaoInput();
        item.setQuantidade(BigDecimal.ONE);
        item.setUnidade("KG");

        AliquotaImpostoSeletivoModel aliquota = AliquotaImpostoSeletivoModel.builder()
                .aliquotaAdRem(BigDecimal.ONE)
                .unidadeMedida("UN")
                .build();

        assertThatThrownBy(() -> service.validarQuantidadeEUnidade(item, aliquota))
                .isInstanceOf(ErroGenericoValidacaoException.class)
                .hasMessageContaining("é diferente da unidade de medida da alíquota");
    }

    @Test
    void naoDeveLancarErroQuandoNaoHouverAliquotaAdRem() {
        ItemOperacaoInput item = new ItemOperacaoInput();
        AliquotaImpostoSeletivoModel aliquota = AliquotaImpostoSeletivoModel.builder()
                .build();

        // assertThatCode garante que nenhuma exceção é lançada
        assertThatCode(() -> service.validarQuantidadeEUnidade(item, aliquota))
                .doesNotThrowAnyException();
    }
    
    @Test
    void naoDeveLancarErroQuandoTudoValido() {
        ItemOperacaoInput item = new ItemOperacaoInput();
        item.setQuantidade(BigDecimal.TEN);
        item.setUnidade("UN");

        AliquotaImpostoSeletivoModel aliquota = AliquotaImpostoSeletivoModel.builder()
                .aliquotaAdRem(BigDecimal.ONE)
                .unidadeMedida("UN")
                .build();

        // assertThatCode garante que nenhuma exceção é lançada
        assertThatCode(() -> service.validarQuantidadeEUnidade(item, aliquota))
                .doesNotThrowAnyException();
    }
}