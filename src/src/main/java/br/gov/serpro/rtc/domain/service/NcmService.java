/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.gov.serpro.rtc.domain.repository.NcmRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NcmService {

    private final NcmRepository repository;

    public boolean existeNcm(String ncm, LocalDate data) {
        return repository.existeNcm(ncm, data);
    }

}