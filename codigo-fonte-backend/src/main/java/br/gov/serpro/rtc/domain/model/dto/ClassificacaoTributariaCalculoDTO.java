package br.gov.serpro.rtc.domain.model.dto;

public record ClassificacaoTributariaCalculoDTO(Long id, String codigo, String tipoAliquota,
        Boolean inGrupoMonofasiaPadrao, Boolean inGrupoMonofasiaReten, Boolean inGrupoMonofasiaRet,
        Boolean inGrupoMonofasiaDiferimento, Boolean inGrupoReducao) {
}
