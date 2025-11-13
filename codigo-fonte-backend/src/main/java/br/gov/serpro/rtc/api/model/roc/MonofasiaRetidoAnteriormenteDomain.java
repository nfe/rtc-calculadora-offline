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
@JsonPropertyOrder({ "qBCMonoRet", "adRemIBSRet", "vIBSMonoRet", "adRemCBSRet", "vCBSMonoRet" })
public class MonofasiaRetidoAnteriormenteDomain implements SerializationVisibility {

    @JsonSerialize(using = BigDecimalTDec1104OpRTCSerializer.class)
    @Schema(description = "Quantidade tributada retida anteriormente")
    @Builder.Default
    private BigDecimal qBCMonoRet = ZERO;    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota ad rem do IBS retido anteriormente")
    @Builder.Default
    private BigDecimal adRemIBSRet = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor do IBS retido anteriormente")
    @Builder.Default
    private BigDecimal vIBSMonoRet = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec0302_04Serializer.class)
    @Schema(description = "Alíquota ad rem da CBS retida anteriormente")
    @Builder.Default
    private BigDecimal adRemCBSRet = ZERO;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor da CBS retida anteriormente")
    @Builder.Default
    private BigDecimal vCBSMonoRet = ZERO;
       
    @JsonIgnore
    public static MonofasiaRetidoAnteriormenteDomain create(CbsIbsOutput cbs, CbsIbsOutput ibsEstadual, CbsIbsOutput ibsMunicipal) {
        var builder = getMonofasiaIBS(ibsEstadual, ibsMunicipal, getMonofasiaCBS(cbs));
        if (builder != null) {
            return builder.build();
        }
        return null;
    }
    
    @JsonIgnore
    private static MonofasiaRetidoAnteriormenteDomainBuilder getMonofasiaCBS(CbsIbsOutput cbs) {
        return getTributoRetido(cbs)
                .map(t -> MonofasiaRetidoAnteriormenteDomain.builder()
                        .qBCMonoRet(t.getQuantidade())
                        .adRemCBSRet(t.getAliquotaAdRem())
                        .vCBSMonoRet(t.getValor()))
                .orElse(null);
    }
    
    @JsonIgnore
    private static MonofasiaRetidoAnteriormenteDomainBuilder getMonofasiaIBS(CbsIbsOutput ibsUF, CbsIbsOutput ibsMun, MonofasiaRetidoAnteriormenteDomainBuilder b) {
        var monoUF = getTributoRetido(ibsUF);
        //var monoMun = getTributoRetido(ibsMun);
        var monoMun = Optional.<GrupoEtapaMonofasiaOutput>empty(); // desconsiderar retenção municipal por enquanto
        if (monoUF.isPresent() || monoMun.isPresent()) {
            var t = GrupoEtapaMonofasiaOutput.merge(monoUF.orElse(null), monoMun.orElse(null));
            if (b == null) {
                b = MonofasiaRetidoAnteriormenteDomain.builder();
            }
            return b.qBCMonoRet(t.getQuantidade())
                    .adRemIBSRet(t.getAliquotaAdRem())
                    .vIBSMonoRet(t.getValor());
        }

        return null;
    }
    
    @JsonIgnore
    private static Optional<GrupoEtapaMonofasiaOutput> getTributoRetido(CbsIbsOutput o) {
        return o != null && o.getGrupoMonofasia() != null 
                ? Optional.ofNullable(o.getGrupoMonofasia().getTributoRetido())
                : Optional.empty();
    }
    
}
