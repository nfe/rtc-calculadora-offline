package br.gov.serpro.rtc.config.xml;

import br.gov.serpro.rtc.api.model.roc.TributosTotais;

/**
 * Necessário para configurar a serialização dos valores totais no XML.
 */
public interface IValoresTotais {
    TributosTotais getTribCalc();
}