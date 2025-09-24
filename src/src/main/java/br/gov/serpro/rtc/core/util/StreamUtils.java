/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.core.util;

import java.util.List;
import java.util.stream.Stream;

public final class StreamUtils {

    private static final int QUANTIDADE_MINIMA_ITENS_PARALELIZAR = 20;

    private StreamUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final <T> Stream<T> from(List<T> itens) {
        return itens.size() > QUANTIDADE_MINIMA_ITENS_PARALELIZAR ? itens.parallelStream() : itens.stream();
    }

    public static final <T> Stream<T> nullSafe(List<T> itens) {
        return itens == null ? Stream.empty() : from(itens);
    }

}
