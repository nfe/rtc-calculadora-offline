package br.gov.serpro.rtc.api.model.roc;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.api.model.SerializationVisibility;
import br.gov.serpro.rtc.api.model.output.CbsIbsOutput;
import br.gov.serpro.rtc.api.model.output.GrupoEtapaMonofasiaOutput;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec0302_04Serializer;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1104OpRTCSerializer;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "qBCMonoReten", "adRemIBSReten", "vIBSMonoReten", "adRemCBSReten", "vCBSMonoReten" })
public class MonofasiaRetencaoDomain implements SerializationVisibility {

    @JsonSerialize(using = BigDecimalTDec1104OpRTCSerializer.class)
    @Schema(description = "Quantidade tributada sujeita à retenção na monofasia")
    @Builder.Default
    private BigDecimal qBCMonoReten = ZERO;    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota ad rem do IBS sujeito a retenção")
    @Builder.Default
    private BigDecimal adRemIBSReten = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do IBS monofásico sujeito a retenção")
    @Builder.Default
    private BigDecimal vIBSMonoReten = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota ad rem da CBS sujeita a retenção")
    @Builder.Default
    private BigDecimal adRemCBSReten = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor da CBS monofásica sujeita a retenção")
    @Builder.Default
    private BigDecimal vCBSMonoReten = ZERO;
    
    @JsonIgnore
    public static MonofasiaRetencaoDomain create(CbsIbsOutput cbs, CbsIbsOutput ibsEstadual, CbsIbsOutput ibsMunicipal) {
        var builder = getMonofasiaIBS(ibsEstadual, ibsMunicipal, getMonofasiaCBS(cbs));
        if (builder != null) {
            return builder.build();
        }
        return null;
    }
    
    @JsonIgnore
    private static MonofasiaRetencaoDomainBuilder getMonofasiaCBS(CbsIbsOutput cbs) {
        return getTributoSujeitoRetencao(cbs)
                .map(t -> MonofasiaRetencaoDomain.builder()
                        .qBCMonoReten(t.getQuantidade())
                        .adRemCBSReten(t.getAliquotaAdRem())
                        .vCBSMonoReten(t.getValor()))
                .orElse(null);
    }
    
    @JsonIgnore
    private static MonofasiaRetencaoDomainBuilder getMonofasiaIBS(CbsIbsOutput ibsUF, CbsIbsOutput ibsMun, MonofasiaRetencaoDomainBuilder b) {
        var monoUF = getTributoSujeitoRetencao(ibsUF);
        //var monoMun = getTributoSujeitoRetencao(ibsMun);
        var monoMun = Optional.<GrupoEtapaMonofasiaOutput>empty(); // desconsiderar retenção municipal por enquanto
        if (monoUF.isPresent() || monoMun.isPresent()) {
            var t = GrupoEtapaMonofasiaOutput.merge(monoUF.orElse(null), monoMun.orElse(null));
            if (b == null) {
                b = MonofasiaRetencaoDomain.builder();
            }
            return b.qBCMonoReten(t.getQuantidade())
                    .adRemIBSReten(t.getAliquotaAdRem())
                    .vIBSMonoReten(t.getValor());
        }

        return null;
    }
    
    @JsonIgnore
    private static Optional<GrupoEtapaMonofasiaOutput> getTributoSujeitoRetencao(CbsIbsOutput o) {
        return o != null && o.getGrupoMonofasia() != null 
                ? Optional.ofNullable(o.getGrupoMonofasia().getTributoSujeitoRetencao())
                : Optional.empty();
    }
}
