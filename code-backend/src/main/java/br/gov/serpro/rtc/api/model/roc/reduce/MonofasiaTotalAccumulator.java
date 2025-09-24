package br.gov.serpro.rtc.api.model.roc.reduce;

import static java.math.BigDecimal.ZERO;
import static java.util.Objects.requireNonNullElse;

import java.math.BigDecimal;

import br.gov.serpro.rtc.api.model.roc.Monofasia;
import br.gov.serpro.rtc.api.model.roc.MonofasiaTotal;

public class MonofasiaTotalAccumulator {
    private final BigDecimal vIBSMono;
    private final BigDecimal vCBSMono;
    private final BigDecimal vIBSMonoReten;
    private final BigDecimal vCBSMonoReten;
    private final BigDecimal vIBSMonoRet;
    private final BigDecimal vCBSMonoRet;

    public MonofasiaTotalAccumulator() {
        this(ZERO, ZERO, ZERO, ZERO, ZERO, ZERO);
    }

    public MonofasiaTotalAccumulator(BigDecimal vIBSMono, BigDecimal vCBSMono, BigDecimal vIBSMonoReten,
            BigDecimal vCBSMonoReten, BigDecimal vIBSMonoRet, BigDecimal vCBSMonoRet) {
        this.vIBSMono = requireNonNullElse(vIBSMono, ZERO);
        this.vCBSMono = requireNonNullElse(vCBSMono, ZERO);
        this.vIBSMonoReten = requireNonNullElse(vIBSMonoReten, ZERO);
        this.vCBSMonoReten = requireNonNullElse(vCBSMonoReten, ZERO);
        this.vIBSMonoRet = requireNonNullElse(vIBSMonoRet, ZERO);
        this.vCBSMonoRet = requireNonNullElse(vCBSMonoRet, ZERO);
    }

    public static MonofasiaTotalAccumulator from(Monofasia m) {
        if (m == null) {
            return new MonofasiaTotalAccumulator();
        }
        return new MonofasiaTotalAccumulator(
            m.getVIBSMono(),
            m.getVCBSMono(),
            m.getVIBSMonoReten(),
            m.getVCBSMonoReten(),
            m.getVIBSMonoRet(),
            m.getVCBSMonoRet());
    }
    
    public MonofasiaTotalAccumulator add(MonofasiaTotalAccumulator other) {
        if (other == null) {
            return this;
        }
        return new MonofasiaTotalAccumulator(
            this.vIBSMono.add(other.vIBSMono),
            this.vCBSMono.add(other.vCBSMono),
            this.vIBSMonoReten.add(other.vIBSMonoReten),
            this.vCBSMonoReten.add(other.vCBSMonoReten),
            this.vIBSMonoRet.add(other.vIBSMonoRet),
            this.vCBSMonoRet.add(other.vCBSMonoRet));
    }

    public MonofasiaTotal toMonofasiaTotal() {
        MonofasiaTotal total = new MonofasiaTotal();
        total.setVIBSMono(this.vIBSMono);
        total.setVCBSMono(this.vCBSMono);
        total.setVIBSMonoReten(this.vIBSMonoReten);
        total.setVCBSMonoReten(this.vCBSMonoReten);
        total.setVIBSMonoRet(this.vIBSMonoRet);
        total.setVCBSMonoRet(this.vCBSMonoRet);
        return total;
    }
}