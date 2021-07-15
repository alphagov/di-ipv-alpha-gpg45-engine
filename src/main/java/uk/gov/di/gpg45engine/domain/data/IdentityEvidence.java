package uk.gov.di.gpg45engine.domain.data;

import lombok.Data;
import uk.gov.di.gpg45engine.domain.gpg45.EvidenceScore;

import java.util.UUID;

@Data
public class IdentityEvidence {
    private UUID uuid;
    private EvidenceType type;
    private Object evidenceData;
    private ValidityCheck validityChecks;
    private EvidenceScore evidenceScore;
}
