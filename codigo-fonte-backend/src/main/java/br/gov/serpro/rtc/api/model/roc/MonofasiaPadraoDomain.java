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
@JsonPropertyOrder({ "qBCMono", "adRemIBS", "adRemCBS", "vIBSMono", "vCBSMono" })
public class MonofasiaPadraoDomain implements SerializationVisibility {
    @JsonSerialize(using = BigDecimalTDec1104OpRTCSerializer.class)
    @Schema(description = "Quantidade tributada na monofasia")
    @Builder.Default
    private BigDecimal qBCMono = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota ad rem do IBS")
    @Builder.Default
    private BigDecimal adRemIBS = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota ad rem da CBS")
    @Builder.Default
    private BigDecimal adRemCBS = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do IBS monofásico")
    @Builder.Default
    private BigDecimal vIBSMono = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor da CBS monofásica")
    @Builder.Default
    private BigDecimal vCBSMono = ZERO;
    
    @JsonIgnore
    public static MonofasiaPadraoDomain create(CbsIbsOutput cbs, CbsIbsOutput ibsEstadual, CbsIbsOutput ibsMunicipal) {
        var builder = getMonofasiaIBS(ibsEstadual, ibsMunicipal, getMonofasiaCBS(cbs));
        if (builder != null) {
            return builder.build();
        }
        return null;
    }
    
    @JsonIgnore
    private static MonofasiaPadraoDomainBuilder getMonofasiaCBS(CbsIbsOutput cbs) {
        return getTributoMonofasico(cbs)
                .map(t -> MonofasiaPadraoDomain.builder()
                        .qBCMono(t.getQuantidade())
                        .adRemCBS(t.getAliquotaAdRem())
                        .vCBSMono(t.getValor()))
                .orElse(null);
    }
    
    @JsonIgnore
    private static MonofasiaPadraoDomainBuilder getMonofasiaIBS(CbsIbsOutput ibsUF, CbsIbsOutput ibsMun, MonofasiaPadraoDomainBuilder b) {
        var monoUF = getTributoMonofasico(ibsUF);
        //var monoMun = getTributoMonofasico(ibsMun);
        var monoMun = Optional.<GrupoEtapaMonofasiaOutput>empty(); // desconsiderar monofasia municipal por enquanto
        if (monoUF.isPresent() || monoMun.isPresent()) {
            var t = GrupoEtapaMonofasiaOutput.merge(monoUF.orElse(null), monoMun.orElse(null));
            if (b == null) {
                b = MonofasiaPadraoDomain.builder();
            }
            return b.qBCMono(t.getQuantidade())
                    .adRemIBS(t.getAliquotaAdRem())
                    .vIBSMono(t.getValor());
        }

        return null;
    }
    
    @JsonIgnore
    private static Optional<GrupoEtapaMonofasiaOutput> getTributoMonofasico(CbsIbsOutput o) {
        return o != null && o.getGrupoMonofasia() != null 
                ? Optional.ofNullable(o.getGrupoMonofasia().getTributoMonofasico())
                : Optional.empty();
    }
}
