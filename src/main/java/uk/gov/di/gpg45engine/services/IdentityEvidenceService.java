package uk.gov.di.gpg45engine.services;

import uk.gov.di.gpg45engine.domain.data.IdentityEvidence;
import uk.gov.di.gpg45engine.domain.gpg45.Score;

public interface IdentityEvidenceService {

    Score getEvidenceStrength(IdentityEvidence identityEvidence);

    Score getEvidenceValidity(IdentityEvidence identityEvidence);
}
