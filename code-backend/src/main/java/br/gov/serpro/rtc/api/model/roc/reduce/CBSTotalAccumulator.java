package br.gov.serpro.rtc.api.model.roc.reduce;

import static java.math.BigDecimal.ZERO;
import static java.util.Objects.requireNonNullElse;

import java.math.BigDecimal;

import br.gov.serpro.rtc.api.model.roc.CBS;
import br.gov.serpro.rtc.api.model.roc.CBSTotal;
import br.gov.serpro.rtc.api.model.roc.CreditoPresumido;

public class CBSTotalAccumulator {
    private final BigDecimal vDif;
    private final BigDecimal vDevTrib;
    private final BigDecimal vCBS;
    private final BigDecimal vCredPres;
    private final BigDecimal vCredPresCondSus;

    public CBSTotalAccumulator() {
        this(ZERO, ZERO, ZERO, ZERO, ZERO);
    }

    public CBSTotalAccumulator(BigDecimal vDif, BigDecimal vDevTrib, BigDecimal vCBS, BigDecimal vCredPres,
            BigDecimal vCredPresCondSus) {
        this.vDif = requireNonNullElse(vDif, ZERO);
        this.vDevTrib = requireNonNullElse(vDevTrib, ZERO);
        this.vCBS = requireNonNullElse(vCBS, ZERO);
        this.vCredPres = requireNonNullElse(vCredPres, ZERO);
        this.vCredPresCondSus = requireNonNullElse(vCredPresCondSus, ZERO);
    }

    public static CBSTotalAccumulator from(CBS cbs, CreditoPresumido cbsCredPres) {
        return new CBSTotalAccumulator(
            cbs != null ? cbs.getVDif() : ZERO,
            cbs != null ? cbs.getVDevTrib() : ZERO,
            cbs != null ? requireNonNullElse(cbs.getValorImposto(), ZERO) : ZERO,
            cbsCredPres != null ? requireNonNullElse(cbsCredPres.getVCredPres(), ZERO) : ZERO,
            cbsCredPres != null ? requireNonNullElse(cbsCredPres.getVCredPresCondSus(), ZERO) : ZERO);
    }

    public CBSTotalAccumulator add(CBSTotalAccumulator other) {
        if (other == null) {
            return this;
        }
        return new CBSTotalAccumulator(
            this.vDif.add(other.vDif),
            this.vDevTrib.add(other.vDevTrib),
            this.vCBS.add(other.vCBS),
            this.vCredPres.add(other.vCredPres),
            this.vCredPresCondSus.add(other.vCredPresCondSus));
    }

    public CBSTotal toCBSTotal() {
        CBSTotal total = new CBSTotal();
        total.setVDif(this.vDif);
        total.setVDevTrib(this.vDevTrib);
        total.setVCBS(this.vCBS);
        total.setVCredPres(this.vCredPres);
        total.setVCredPresCondSus(this.vCredPresCondSus);
        return total;
    }
}