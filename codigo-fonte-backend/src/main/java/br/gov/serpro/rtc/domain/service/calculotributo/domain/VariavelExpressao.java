package br.gov.serpro.rtc.domain.service.calculotributo.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum VariavelExpressao {
    BASE_CALCULO("baseCalculo"),
    IMPOSTO_SELETIVO_CALCULADO("impostoSeletivoCalculado"),
    BASE_CALCULO_INFORMADA("baseCalculoInformada"),
    IMPOSTO_SELETIVO_INFORMADO("impostoSeletivoInformado"),
    ALIQUOTA_EFETIVA("aliquotaEfetiva"),
    REDUTOR("redutor"),
    AJUSTE("ajuste"),
    ALIQUOTA_AD_REM("aliquotaAdRem"),
    ALIQUOTA_AD_VALOREM("aliquotaAdValorem"),
    ALIQUOTA_REFERENCIA("aliquotaReferencia"),
    ALIQUOTA_PADRAO_OU_REFERENCIA("aliquotaPadraoOuReferencia"),
    PERCENTUAL_REDUCAO("percentualReducao"),
    ALIQUOTA("aliquota"),
    QUANTIDADE("quantidade"),
    TRIBUTO_CALCULADO("tributoCalculado"),
    PERCENTUAL_DIFERIMENTO("percentualDiferimento");
    
    private final String nome;
}
