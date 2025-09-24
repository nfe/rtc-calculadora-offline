/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.model.entity.ClassificacaoTributaria;
import br.gov.serpro.rtc.domain.repository.ClassificacaoTributariaRepository;
import br.gov.serpro.rtc.domain.service.exception.ClassificacaoTributariaNaoEncontradaException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClassificacaoTributariaService {

    private final ClassificacaoTributariaRepository repository;

    public ClassificacaoTributaria buscarClassificacaoTributariaCbsIbs(String codigo, LocalDate data) {
        return repository.buscarClassificacaoTributariaCbsIbs(codigo, data)
                .orElseThrow(() -> new ClassificacaoTributariaNaoEncontradaException(codigo, "CBS e IBS", data));
    }

    public ClassificacaoTributaria buscarClassificacaoTributariaImpostoSeletivo(String codigo, LocalDate data) {
        return repository.buscarClassificacaoTributariaImpostoSeletivo(codigo, data)
                .orElseThrow(() -> new ClassificacaoTributariaNaoEncontradaException(codigo, "Imposto Seletivo", data));
    }

}