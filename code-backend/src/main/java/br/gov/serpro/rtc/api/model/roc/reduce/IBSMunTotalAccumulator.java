package br.gov.serpro.rtc.api.model.roc.reduce;

import static java.math.BigDecimal.ZERO;
import static java.util.Objects.requireNonNullElse;

import java.math.BigDecimal;

import br.gov.serpro.rtc.api.model.roc.IBSMun;
import br.gov.serpro.rtc.api.model.roc.IBSMunTotal;

public class IBSMunTotalAccumulator {
    private final BigDecimal vDif;
    private final BigDecimal vDevTrib;
    private final BigDecimal vIBSMun;

    public IBSMunTotalAccumulator() {
        this(ZERO, ZERO, ZERO);
    }

    public IBSMunTotalAccumulator(BigDecimal vDif, BigDecimal vDevTrib, BigDecimal vIBSMun) {
        this.vDif = requireNonNullElse(vDif, ZERO);
        this.vDevTrib = requireNonNullElse(vDevTrib, ZERO);
        this.vIBSMun = requireNonNullElse(vIBSMun, ZERO);
    }

    public static IBSMunTotalAccumulator from(IBSMun ibsMun) {
        if (ibsMun == null) {
            return new IBSMunTotalAccumulator();
        }
        return new IBSMunTotalAccumulator(
            requireNonNullElse(ibsMun.getVDif(), ZERO),
            requireNonNullElse(ibsMun.getVDevTrib(), ZERO),
            requireNonNullElse(ibsMun.getValorImposto(), ZERO));
    }
    
    public IBSMunTotalAccumulator add(IBSMunTotalAccumulator other) {
        if (other == null) {
            return this;
        }
        return new IBSMunTotalAccumulator(
            this.vDif.add(other.vDif),
            this.vDevTrib.add(other.vDevTrib),
            this.vIBSMun.add(other.vIBSMun));
    }

    public IBSMunTotal toIBSMunTotal() {
        IBSMunTotal total = new IBSMunTotal();
        total.setVDif(this.vDif);
        total.setVDevTrib(this.vDevTrib);
        total.setVIBSMun(this.vIBSMun);
        return total;
    }
}