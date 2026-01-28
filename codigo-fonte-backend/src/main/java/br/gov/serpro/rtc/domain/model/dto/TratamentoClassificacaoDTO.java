package br.gov.serpro.rtc.domain.model.dto;

public record TratamentoClassificacaoDTO(Long idTratamentoTributario, Long idClassificacaoTributaria,
        boolean inIncompativelComSuspensao, boolean inExigeGrupoDesoneracao) {
}
