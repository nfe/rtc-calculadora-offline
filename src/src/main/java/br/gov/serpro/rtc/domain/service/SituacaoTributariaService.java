/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.repository.SituacaoTributariaRepository;
import br.gov.serpro.rtc.domain.service.exception.SituacaoTributariaNaoEncontradaException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SituacaoTributariaService {

    private final SituacaoTributariaRepository repository;

    public void validarCst(String cst, Long idTributo, LocalDate data) {
        if (!repository.existeCst(cst, idTributo, data)) {
            String tributo = idTributo == 1L ? "Imposto Seletivo" : "CBS e IBS";
            throw new SituacaoTributariaNaoEncontradaException(cst, tributo, data);
        }
    }

}