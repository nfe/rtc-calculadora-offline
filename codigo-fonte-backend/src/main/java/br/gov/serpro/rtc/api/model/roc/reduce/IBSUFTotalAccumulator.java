package br.gov.serpro.rtc.api.model.roc.reduce;

import static java.math.BigDecimal.ZERO;
import static java.util.Objects.requireNonNullElse;

import java.math.BigDecimal;

import br.gov.serpro.rtc.api.model.roc.IBSUFDomain;
import br.gov.serpro.rtc.api.model.roc.IBSUFTotalDomain;

public class IBSUFTotalAccumulator {
    private final BigDecimal vDif;
    private final BigDecimal vDevTrib;
    private final BigDecimal vIBSUF;

    public IBSUFTotalAccumulator() {
        this(ZERO, ZERO, ZERO);
    }

    public IBSUFTotalAccumulator(BigDecimal vDif, BigDecimal vDevTrib, BigDecimal vIBSUF) {
        this.vDif = requireNonNullElse(vDif, ZERO);
        this.vDevTrib = requireNonNullElse(vDevTrib, ZERO);
        this.vIBSUF = requireNonNullElse(vIBSUF, ZERO);
    }
    
    public static IBSUFTotalAccumulator from(IBSUFDomain ibsUf) {
        if (ibsUf == null) {
            return new IBSUFTotalAccumulator();
        }
        return new IBSUFTotalAccumulator(
                ibsUf.getVDif(),
                ibsUf.getVDevTrib(),
                ibsUf.getVIBSUF());
    }

    public IBSUFTotalAccumulator add(IBSUFTotalAccumulator other) {
        if (other == null) {
            return this;
        }
        return new IBSUFTotalAccumulator(
            this.vDif.add(other.vDif),
            this.vDevTrib.add(other.vDevTrib),
            this.vIBSUF.add(other.vIBSUF));
    }

    public IBSUFTotalDomain toIBSUFTotal() {
        IBSUFTotalDomain total = new IBSUFTotalDomain();
        total.setVDif(this.vDif);
        total.setVDevTrib(this.vDevTrib);
        total.setVIBSUF(this.vIBSUF);
        return total;
    }
}