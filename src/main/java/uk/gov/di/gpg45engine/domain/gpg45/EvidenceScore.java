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
}
