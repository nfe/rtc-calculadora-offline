package br.gov.serpro.rtc.domain.service;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import br.gov.serpro.rtc.api.model.input.ItemOperacaoInput;
import br.gov.serpro.rtc.api.model.input.OperacaoInput;
import br.gov.serpro.rtc.api.model.input.ImpostoSeletivoInput;
import br.gov.serpro.rtc.domain.model.enumeration.TipoWarningDadosSimulados;
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
    
    @Test
    void deveRetornarNullQuandoDataAnteriorA2027() {
        OperacaoInput operacao = new OperacaoInput();
        operacao.setDhFatoGerador(OffsetDateTime.parse("2026-12-31T23:59:59-03:00"));
        operacao.setItens(List.of(new ItemOperacaoInput()));
        
        TipoWarningDadosSimulados resultado = service.getWarningDadosSimulados(operacao);
        
        assertThat(resultado).isNull();
    }
    
        @ParameterizedTest
        @ValueSource(strings = {"010", "220", "221"})
        void deveRetornarCasoAliquotasFicticiasParaQualquerData(String cst) {
                List<String> datas = List.of(
                        "2026-01-01T03:00:00-03:00",
                        "2026-12-31T23:59:59-03:00",
                        "2027-01-01T00:00:00-03:00",
                        "2028-05-10T12:00:00-03:00"
                );
                for (String data : datas) {
                        OperacaoInput operacao = new OperacaoInput();
                        operacao.setDhFatoGerador(OffsetDateTime.parse(data));
                        ItemOperacaoInput item = new ItemOperacaoInput();
                        item.setCst(cst);
                        operacao.setItens(List.of(item));
                        TipoWarningDadosSimulados resultado = service.getWarningDadosSimulados(operacao);
                        assertThat(resultado)
                                .withFailMessage("Esperado CASO_ALIQUOTAS_FICTICIAS para CST %s na data %s", cst, data)
                                .isEqualTo(TipoWarningDadosSimulados.CASO_ALIQUOTAS_FICTICIAS);
                        assertThat(resultado.getValor()).isEqualTo(5);
                }
        }
    
    @Test
    void deveRetornarCasoImpostoSeletivo() {
        OperacaoInput operacao = new OperacaoInput();
        operacao.setDhFatoGerador(OffsetDateTime.parse("2027-01-01T03:00:00-03:00"));
        
        ItemOperacaoInput item = new ItemOperacaoInput();
        item.setCst("000");
        item.setImpostoSeletivo(new ImpostoSeletivoInput());
        operacao.setItens(List.of(item));
        
        TipoWarningDadosSimulados resultado = service.getWarningDadosSimulados(operacao);
        
        assertThat(resultado).isEqualTo(TipoWarningDadosSimulados.CASO_IMPOSTO_SELETIVO);
        assertThat(resultado.getValor()).isEqualTo(3);
    }
    
    @Test
    void deveRetornarCasoGeral() {
        OperacaoInput operacao = new OperacaoInput();
        operacao.setDhFatoGerador(OffsetDateTime.parse("2027-01-01T03:00:00-03:00"));
        
        ItemOperacaoInput item = new ItemOperacaoInput();
        item.setCst("000");
        operacao.setItens(List.of(item));
        
        TipoWarningDadosSimulados resultado = service.getWarningDadosSimulados(operacao);
        
        assertThat(resultado).isEqualTo(TipoWarningDadosSimulados.CASO_GERAL);
        assertThat(resultado.getValor()).isEqualTo(1);
    }
}