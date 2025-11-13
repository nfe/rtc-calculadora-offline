package br.gov.serpro.rtc.api.model.roc;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.serpro.rtc.api.model.SerializationVisibility;
import br.gov.serpro.rtc.config.serializer.BigDecimalTDec1302Serializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "gIBSUF", "gIBSMun", "vIBS", "vCredPres", "vCredPresCondSus" })
public class IBSTotalDomain implements SerializationVisibility {

    @Schema(description = "Grupo total do IBS da UF")
    private IBSUFTotalDomain gIBSUF;
    
    @Schema(description = "Grupo total do IBS do Município")
    private IBSMunTotalDomain gIBSMun;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do IBS")
    private BigDecimal vIBS;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do crédito presumido")
    private BigDecimal vCredPres;
    
    @JsonSerialize(using = BigDecimalTDec1302Serializer.class)
    @Schema(description = "Valor total do crédito presumido em condição suspensiva")
    private BigDecimal vCredPresCondSus;
    
    // TODO Verificar se é possível criar um IBSCBSTotal
    public static IBSTotalDomain create(List<ObjetoDomain> detalhes) {
        if (detalhes != null && !detalhes.isEmpty()) {
            final var ibsTotal = new IBSTotalDomain();
            final var gIBSUF = IBSUFTotalDomain.create(detalhes);
            final var gIBSMun = IBSMunTotalDomain.create(detalhes);
            
            // Garantir que gIBSUF e gIBSMun sempre existam, mesmo que zerados
            // Necessário para validação do XML
            ibsTotal.setGIBSUF(gIBSUF != null ? gIBSUF : new IBSUFTotalDomain());
            ibsTotal.setGIBSMun(gIBSMun != null ? gIBSMun : new IBSMunTotalDomain());
            
            ibsTotal.setVIBS(getValorIbsTotal(ibsTotal.getGIBSUF(), ibsTotal.getGIBSMun()));
            ibsTotal.setVCredPres(getValorCreditoPresumidoTotal(detalhes));
            ibsTotal.setVCredPresCondSus(getValorCreditoPresumidoCondSusTotal(detalhes));
            return ibsTotal;
        }
        return null;
    }

    private static BigDecimal getValorIbsTotal(IBSUFTotalDomain ibsUFTotal, IBSMunTotalDomain ibsMunTotal) {
        BigDecimal vIBSUF = ibsUFTotal != null ? ibsUFTotal.getVIBSUF() : ZERO;
        BigDecimal vIBSMun = ibsMunTotal != null ? ibsMunTotal.getVIBSMun() : ZERO;
        return vIBSUF.add(vIBSMun);
    }
    
    private static BigDecimal getValorCreditoPresumidoTotal(List<ObjetoDomain> detalhes) {
        return detalhes.parallelStream()
                .filter(ObjetoDomain::possuiCredPresIbs)
                .map(ObjetoDomain::getVCredPresIbs)
                .reduce(ZERO, BigDecimal::add);
    }

    private static BigDecimal getValorCreditoPresumidoCondSusTotal(List<ObjetoDomain> detalhes) {
        return detalhes.parallelStream()
                .filter(ObjetoDomain::possuiCredPresCondSusIbs)
                .map(ObjetoDomain::getVCredPresCondSusIbs)
                .reduce(ZERO, BigDecimal::add);
    }

}