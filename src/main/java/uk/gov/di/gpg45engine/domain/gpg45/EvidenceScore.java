package uk.gov.di.gpg45engine.domain.gpg45;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
@Builder
public class EvidenceScore {

    @Builder.Default
    private Score strength = Score.NOT_AVAILABLE;

    @Builder.Default
    private Score validity = Score.NOT_AVAILABLE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EvidenceScore that = (EvidenceScore) o;
        return this.hashCode() == that.hashCode();
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }
}
