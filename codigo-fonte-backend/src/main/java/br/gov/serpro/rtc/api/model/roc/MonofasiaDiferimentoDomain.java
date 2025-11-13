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
import br.gov.serpro.rtc.api.model.output.GrupoDiferimentoMonofasiaOutput;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec0302_04Serializer;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "pDifIBS", "vIBSMonoDif", "pDifCBS", "vCBSMonoDif" })
public class MonofasiaDiferimentoDomain implements SerializationVisibility {
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Percentual do diferimento do imposto monofásico do IBS")
    @Builder.Default
    private BigDecimal pDifIBS = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do IBS monofásico diferido")
    @Builder.Default
    private BigDecimal vIBSMonoDif = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Percentual do diferimento do imposto monofásico da CBS")
    @Builder.Default
    private BigDecimal pDifCBS = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor da CBS monofásica diferida")
    @Builder.Default
    private BigDecimal vCBSMonoDif = ZERO;
    
    @JsonIgnore
    public static MonofasiaDiferimentoDomain create(CbsIbsOutput cbs, CbsIbsOutput ibsEstadual, CbsIbsOutput ibsMunicipal) {
        var builder = getMonofasiaIBS(ibsEstadual, ibsMunicipal, getMonofasiaCBS(cbs));
        if (builder != null) {
            return builder.build();
        }
        return null;
    }
    
    @JsonIgnore
    private static MonofasiaDiferimentoDomainBuilder getMonofasiaCBS(CbsIbsOutput cbs) {
        return getTributoDiferido(cbs)
                .map(t -> MonofasiaDiferimentoDomain.builder()
                        .pDifCBS(t.getPercentualDiferimento())
                        .vCBSMonoDif(t.getValorDiferimento()))
                .orElse(null);
    }
    
    @JsonIgnore
    private static MonofasiaDiferimentoDomainBuilder getMonofasiaIBS(CbsIbsOutput ibsUF, CbsIbsOutput ibsMun, MonofasiaDiferimentoDomainBuilder b) {
        var monoUF = getTributoDiferido(ibsUF);
        //var monoMun = getTributoDiferido(ibsMun);
        var monoMun = Optional.<GrupoDiferimentoMonofasiaOutput>empty(); // desconsiderar diferimento municipal por enquanto
        if (monoUF.isPresent() || monoMun.isPresent()) {
            var t = GrupoDiferimentoMonofasiaOutput.merge(monoUF.orElse(null), monoMun.orElse(null));
            if (b == null) {
                b = MonofasiaDiferimentoDomain.builder();
            }
            return b.pDifIBS(t.getPercentualDiferimento())
                    .vIBSMonoDif(t.getValorDiferimento());
        }

        return null;
    }
    
    @JsonIgnore
    private static Optional<GrupoDiferimentoMonofasiaOutput> getTributoDiferido(CbsIbsOutput o) {
        return o != null && o.getGrupoMonofasia() != null 
                ? Optional.ofNullable(o.getGrupoMonofasia().getTributoDiferido())
                : Optional.empty();
    }
}
