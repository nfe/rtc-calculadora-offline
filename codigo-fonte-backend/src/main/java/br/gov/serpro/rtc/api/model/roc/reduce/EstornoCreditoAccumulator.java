package br.gov.serpro.rtc.api.model.roc.reduce;

import static java.math.BigDecimal.ZERO;
import static java.util.Objects.requireNonNullElse;

import java.math.BigDecimal;

import br.gov.serpro.rtc.api.model.roc.EstornoCreditoDomain;

public class EstornoCreditoAccumulator {
    private final BigDecimal vIBSEstCred;
    private final BigDecimal vCBSEstCred;

    public EstornoCreditoAccumulator() {
        this(ZERO, ZERO);
    }

    public EstornoCreditoAccumulator(BigDecimal vIBSEstCred, BigDecimal vCBSEstCred) {
        this.vIBSEstCred = requireNonNullElse(vIBSEstCred, ZERO);
        this.vCBSEstCred = requireNonNullElse(vCBSEstCred, ZERO);
    }

    public static EstornoCreditoAccumulator from(EstornoCreditoDomain e) {
        if (e == null) {
            return new EstornoCreditoAccumulator();
        }
        return new EstornoCreditoAccumulator(
            requireNonNullElse(e.getVIBSEstCred(), ZERO),
            requireNonNullElse(e.getVCBSEstCred(), ZERO));
    }
    
    public EstornoCreditoAccumulator add(EstornoCreditoAccumulator other) {
        if (other == null) {
            return this;
        }
        return new EstornoCreditoAccumulator(
            this.vIBSEstCred.add(other.vIBSEstCred),
            this.vCBSEstCred.add(other.vCBSEstCred));
    }

    public EstornoCreditoDomain toEstornoCreditoDomainTotal() {
        EstornoCreditoDomain total = new EstornoCreditoDomain();
        total.setVIBSEstCred(this.vIBSEstCred);
        total.setVCBSEstCred(this.vCBSEstCred);
        return total;
    }
}