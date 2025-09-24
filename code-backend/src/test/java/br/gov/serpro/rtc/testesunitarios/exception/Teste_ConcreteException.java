/*
* Versão de Homologação/Testes
*/
package br.gov.serpro.rtc.testesunitarios.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.gov.serpro.rtc.domain.service.exception.AliquotaAdRemNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.AliquotaReferenciaNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.CampoInvalidoException;
import br.gov.serpro.rtc.domain.service.exception.ClassificacaoTributariaNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.ClassificacaoTributariaNaoVinculadaSituacaoTributariaException;
import br.gov.serpro.rtc.domain.service.exception.DesoneracaoInformadaIndevidamenteException;
import br.gov.serpro.rtc.domain.service.exception.DesoneracaoNaoInformadaException;
import br.gov.serpro.rtc.domain.service.exception.ErroAvaliadorExpressaoAritmeticaException;
import br.gov.serpro.rtc.domain.service.exception.ErroInternoSistemaException;
import br.gov.serpro.rtc.domain.service.exception.FormaAplicacaoNaoDefinidaException;
import br.gov.serpro.rtc.domain.service.exception.FundamentacaoClassificacaoNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.ImpostoSeletivoInformadoIndevidamenteException;
import br.gov.serpro.rtc.domain.service.exception.ImpostoSeletivoNaoInformadoException;
import br.gov.serpro.rtc.domain.service.exception.IncompatibilidadeSuspensaoException;
import br.gov.serpro.rtc.domain.service.exception.ItemDuplicadoException;
import br.gov.serpro.rtc.domain.service.exception.MunicipioNaoEncontradoException;
import br.gov.serpro.rtc.domain.service.exception.MunicipioNaoPertencenteException;
import br.gov.serpro.rtc.domain.service.exception.NbsNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.NbsNaoVinculadaException;
import br.gov.serpro.rtc.domain.service.exception.NcmNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.NcmCompletoNaoInformadoException;
import br.gov.serpro.rtc.domain.service.exception.NcmNaoVinculadaException;
import br.gov.serpro.rtc.domain.service.exception.NbsCompletoNaoInformadoException;
import br.gov.serpro.rtc.domain.service.exception.NcmNbsSimultaneasException;
import br.gov.serpro.rtc.domain.service.exception.NomenclaturaException;
import br.gov.serpro.rtc.domain.service.exception.PercentualReducaoNaoEncontradoException;
import br.gov.serpro.rtc.domain.service.exception.SituacaoTributariaNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.TipoAliquotaDesconhecidoException;
import br.gov.serpro.rtc.domain.service.exception.TratamentoClassificacaoNaoEncontradoException;
import br.gov.serpro.rtc.domain.service.exception.UfNaoEncontradaException;

class Teste_ConcreteException {

    @Test
    void Teste_NcmNaoVinculadaException() {
        // preparar
        String ncm = "24021000";
        String cClassTrib = "200003";
        String tributo = "CBS e IBS";
        // executar e avaliar
        Exception exception = assertThrows(NcmNaoVinculadaException.class, () -> {
            throw new NcmNaoVinculadaException(ncm, cClassTrib, tributo);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_ClassificacaoTributariaNaoEncontradaException() {
        // preparar
        String cClassTrib = "200003";
        String tributo = "CBS e IBS";
        LocalDate data = LocalDate.now();
        // executar e avaliar
        Exception exception = assertThrows(ClassificacaoTributariaNaoEncontradaException.class, () -> {
            throw new ClassificacaoTributariaNaoEncontradaException(cClassTrib, tributo, data);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_TipoAliquotaDesconhecidoException() {
        // preparar
        String cClassTrib = "000001";
        String tipoAliquota = "Padrão";
        String cst = "000";
        // executar e avaliar
        Exception exception = assertThrows(TipoAliquotaDesconhecidoException.class, () -> {
            throw new TipoAliquotaDesconhecidoException(tipoAliquota, cClassTrib, cst);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    // @Test
    // void Teste_TributoSituacaoTributariaNaoEncontradoException() {
    //     // preparar
    //     Long idTributo = 2L;
    //     Long idSituacaoTributaria = 1L;
    //     LocalDate data = LocalDate.now();
    //     // executar e avaliar
    //     Exception exception = assertThrows(TributoSituacaoTributariaNaoEncontradoException.class, () -> {
    //         throw new TributoSituacaoTributariaNaoEncontradoException(idTributo, idSituacaoTributaria, data);
    //     });
    //     assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    // }

    @Test
    void Teste_AliquotaAdRemNaoEncontradaException() {
        // preparar
        String ncm = "24021000";
        Long idTributo = 2L;
        LocalDate data = LocalDate.now();
        // executar e avaliar
        Exception exception = assertThrows(AliquotaAdRemNaoEncontradaException.class, () -> {
            throw new AliquotaAdRemNaoEncontradaException(ncm, idTributo, data);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_SituacaoTributariaNaoEncontradaException() {
        // preparar
        String codigo = "555";
        String tributo = "CBS e IBS";
        LocalDate data = LocalDate.now();
        // executar e avaliar
        Exception exception = assertThrows(SituacaoTributariaNaoEncontradaException.class, () -> {
            throw new SituacaoTributariaNaoEncontradaException(codigo, tributo, data);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_NomenclaturaException() {
        // preparar
        String cClassTrib = "200046";
        String nomenclatura = "IBS";
        String tributo = "CBS e IBS";
        // executar e avaliar
        Exception exception = assertThrows(NomenclaturaException.class, () -> {
            throw new NomenclaturaException("Só se aplica a IBS...");
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_NbsNaoVinculadaException() {
        // preparar
        String nbs = "114052200";
        String cClassTrib = "000001";
        String tributo = "CBS e IBS";
        // executar e avaliar
        Exception exception = assertThrows(NbsNaoVinculadaException.class, () -> {
            throw new NbsNaoVinculadaException(nbs, cClassTrib, tributo);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_PercentualReducaoNaoEncontradoException() {
        // preparar
        Long id = 1L;
        LocalDate data = LocalDate.now();
        // executar e avaliar
        Exception exception = assertThrows(PercentualReducaoNaoEncontradoException.class, () -> {
            throw new PercentualReducaoNaoEncontradoException(id, data);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_IncompatibilidadeSuspensaoException() {
        // preparar
        String cClassTrib = "550001";
        String cst = "550";
        // executar e avaliar
        Exception exception = assertThrows(IncompatibilidadeSuspensaoException.class, () -> {
            throw new IncompatibilidadeSuspensaoException(cClassTrib, cst);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_TratamentoClassificacaoNaoEncontradoException() {
        // preparar
        Long id = 1L;
        LocalDate data = LocalDate.now();
        // executar e avaliar
        Exception exception = assertThrows(TratamentoClassificacaoNaoEncontradoException.class, () -> {
            throw new TratamentoClassificacaoNaoEncontradoException(id, data);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_ImpostoSeletivoNaoInformadoException() {
        // preparar
        String ncm = "24021000";
        LocalDate data = LocalDate.now();
        // executar e avaliar
        Exception exception = assertThrows(ImpostoSeletivoNaoInformadoException.class, () -> {
            throw new ImpostoSeletivoNaoInformadoException(ncm, data);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_NcmNaoEncontradaException() {
        // preparar
        String ncm = "24021000";
        LocalDate data = LocalDate.now();
        // executar e avaliar
        Exception exception = assertThrows(NcmNaoEncontradaException.class, () -> {
            throw new NcmNaoEncontradaException(ncm, data);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_AliquotaReferenciaNaoEncontradaException() {
        // preparar
        Long idTributo = 2L;
        LocalDate data = LocalDate.now();
        // executar e avaliar
        Exception exception = assertThrows(AliquotaReferenciaNaoEncontradaException.class, () -> {
            throw new AliquotaReferenciaNaoEncontradaException(idTributo, data);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_DesoneracaoNaoInformadaException() {
        // preparar
        String cClassTrib = "550001";
        String cst = "550";
        // executar e avaliar
        Exception exception = assertThrows(DesoneracaoNaoInformadaException.class, () -> {
            throw new DesoneracaoNaoInformadaException(cClassTrib, cst);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_DesoneracaoInformadaIndevidamenteException() {
        // preparar
        String cClassTrib = "000001";
        String cst = "000";
        // executar e avaliar
        Exception exception = assertThrows(DesoneracaoInformadaIndevidamenteException.class, () -> {
            throw new DesoneracaoInformadaIndevidamenteException(cClassTrib, cst);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_FundamentacaoClassificacaoNaoEncontradaException() {
        // preparar
        Long id = 1L;
        LocalDate data = LocalDate.now();
        // executar e avaliar
        Exception exception = assertThrows(FundamentacaoClassificacaoNaoEncontradaException.class, () -> {
            throw new FundamentacaoClassificacaoNaoEncontradaException(id, data);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_ImpostoSeletivoInformadoIndevidamenteException() {
        // preparar
        String ncm = "09024000";
        LocalDate data = LocalDate.now();
        // executar e avaliar
        Exception exception = assertThrows(ImpostoSeletivoInformadoIndevidamenteException.class, () -> {
            throw new ImpostoSeletivoInformadoIndevidamenteException(ncm, data);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_NbsNaoEncontradaException() {
        // preparar
        String nbs = "999999999";
        LocalDate data = LocalDate.now();
        // executar e avaliar
        Exception exception = assertThrows(NbsNaoEncontradaException.class, () -> {
            throw new NbsNaoEncontradaException(nbs, data);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_MunicipioNaoPertencenteException() {
        // preparar
        Long idMunicipio = 430192L;
        String siglaUf = "RJ";
        // executar e avaliar
        Exception exception = assertThrows(MunicipioNaoPertencenteException.class, () -> {
            throw new MunicipioNaoPertencenteException(idMunicipio, siglaUf);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_UfNaoEncontradaException() {
        // preparar
        String siglaUf = "RJ";
        // executar e avaliar
        Exception exception = assertThrows(UfNaoEncontradaException.class, () -> {
            throw new UfNaoEncontradaException(siglaUf);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_ErroAvaliadorExpressaoAritmeticaException() {
        // preparar
        String erro = "Operador inválido";
        // executar e avaliar
        Exception exception = assertThrows(ErroAvaliadorExpressaoAritmeticaException.class, () -> {
            throw new ErroAvaliadorExpressaoAritmeticaException(erro);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_ItemDuplicadoException() {
        // preparar
        String item = "1";
        // executar e avaliar
        Exception exception = assertThrows(ItemDuplicadoException.class, () -> {
            throw new ItemDuplicadoException(item);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_MunicipioNaoEncontradoException() {
        // preparar
        Long id = 9999999L;
        // executar e avaliar
        Exception exception = assertThrows(MunicipioNaoEncontradoException.class, () -> {
            throw new MunicipioNaoEncontradoException(id);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_ErroInternoSistemaException() {
        // preparar
        String mensagem = "Erro interno no sistema";
        // executar e avaliar
        Exception exception = assertThrows(ErroInternoSistemaException.class, () -> {
            throw new ErroInternoSistemaException(mensagem);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_FormaAplicacaoNaoDefinidaException() {
        // preparar
        // executar e avaliar
        Exception exception = assertThrows(FormaAplicacaoNaoDefinidaException.class, () -> {
            throw new FormaAplicacaoNaoDefinidaException();
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_NcmNbsSimultaneasException() {
        // preparar
        // executar e avaliar
        Exception exception = assertThrows(NcmNbsSimultaneasException.class, () -> {
            throw new NcmNbsSimultaneasException();
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_NcmCompletoNaoInformadoException() {
        // preparar
        // executar e avaliar
        Exception exception = assertThrows(NcmCompletoNaoInformadoException.class, () -> {
            throw new NcmCompletoNaoInformadoException();
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }
    
    @Test
    void Teste_NbsCompletoNaoInformadoException() {
        // preparar
        // executar e avaliar
        Exception exception = assertThrows(NbsCompletoNaoInformadoException.class, () -> {
            throw new NbsCompletoNaoInformadoException();
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_CampoInvalidoException() {
        // preparar
        String msg = "Campo inválido";
        // executar e avaliar
        Exception exception = assertThrows(CampoInvalidoException.class, () -> {
            throw new CampoInvalidoException(msg);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    @Test
    void Teste_ClassificacaoTributariaNaoVinculadaSituacaoTributariaException() {
        // preparar
        String cClassTrib = "000020";
        String cst = "000";
        String tributo = "Imposto Seletivo";
        // executar e avaliar
        Exception exception = assertThrows(ClassificacaoTributariaNaoVinculadaSituacaoTributariaException.class, () -> {
            throw new ClassificacaoTributariaNaoVinculadaSituacaoTributariaException(cClassTrib, cst, tributo);
        });
        assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    }

    // @Test
    // void Teste_ValidacaoException() {
    //     // preparar
    //     String cClassTrib = "000020";
    //     String cst = "000";
    //     String tributo = "Imposto Seletivo";
    //     // executar e avaliar
    //     Exception exception = assertThrows(ValidacaoException.class, () -> {
    //         throw new ValidacaoException();
    //     });
    //     assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    // }

        // @Test
    // void Teste_NegocioException() {
    //     // preparar
    //     String mensagem = "Erro interno no sistema";
    //     // executar e avaliar
    //     Exception exception = assertThrows(NegocioException.class, () -> {
    //         throw new NegocioException();
    //     });
    //     assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    // }

    // @Test
    // void Teste_EntidadeNaoEncontradaException() {
    //     // preparar
    //     String mensagem = "Erro interno no sistema";
    //     // executar e avaliar
    //     Exception exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
    //         throw new EntidadeNaoEncontradaException();
    //     });
    //     assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    // }

    // @Test
    // void Teste_EstruturaInconsistenteException() {
    //     // preparar
    //     String mensagem = "Erro interno no sistema";
    //     // executar e avaliar
    //     Exception exception = assertThrows(EstruturaInconsistenteException.class, () -> {
    //         throw new EstruturaInconsistenteException();
    //     });
    //     assertEquals(false, exception.getMessage().isEmpty(), "Mensagem de exceção não deve ser vazia");
    // }

}