package uk.gov.di.gpg45engine.domain.data;

import lombok.Data;
import uk.gov.di.gpg45engine.domain.gpg45.EvidenceScore;

@Data
public class IdentityEvidence {
    private EvidenceType type;
    private Object evidenceData;
    private ValidityCheck validityChecks;
    private EvidenceScore evidenceScore;
}
