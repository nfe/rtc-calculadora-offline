/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.model.enumeration;

import java.util.stream.Stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TributoEnum {
    IS(1, "Imposto Seletivo"), CBS(2, "CBS"), IBS_ESTADUAL(3, "IBS Estadual"), IBS_MUNICIPAL(4, "IBS Municipal");

    private final long codigo;
    private final String nome;

    public static TributoEnum get(long codigo) {
        return Stream.of(values()).filter(t -> t.getCodigo() == codigo).findFirst()
                .orElseThrow(() -> new RuntimeException("Tributo não mapeado"));
    }
}
