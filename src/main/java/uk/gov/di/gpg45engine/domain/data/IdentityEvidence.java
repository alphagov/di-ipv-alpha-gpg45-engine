package uk.gov.di.gpg45engine.domain.data;

import lombok.Data;

@Data
public class IdentityEvidence {
    private EvidenceType type;
    private Object evidenceData;
    private ValidityCheck validityChecks;
}
