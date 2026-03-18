package br.gov.serpro.rtc.api.model.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;

import br.gov.serpro.rtc.domain.service.exception.DataFatoGeradorNaoInformadaException;

class OperacaoInputTest {

    @Test
    void retornaDataDoDhFatoGeradorQuandoPresente() {
        OperacaoInput input = new OperacaoInput();
        OffsetDateTime dhFatoGerador = OffsetDateTime.parse("2026-01-05T11:42:49.95-03:00");
        input.setDhFatoGerador(dhFatoGerador);

        LocalDate result = input.getFatoGeradorAplicavel();
        assertEquals(dhFatoGerador.toLocalDate(), result);
    }

    @Test
    void retornaDataDoDataHoraEmissaoQuandoDhFatoGeradorNulo() {
        OperacaoInput input = new OperacaoInput();
        OffsetDateTime dataHoraEmissao = OffsetDateTime.parse("2026-01-10T09:00:00-03:00");
        input.setDataHoraEmissao(dataHoraEmissao);

        LocalDate result = input.getFatoGeradorAplicavel();
        assertEquals(dataHoraEmissao.toLocalDate(), result);
    }

    @Test
    void lancaExcecaoQuandoDatasSaoNulas() {
        OperacaoInput input = new OperacaoInput();
        assertThrows(DataFatoGeradorNaoInformadaException.class, input::getFatoGeradorAplicavel);
    }
}
