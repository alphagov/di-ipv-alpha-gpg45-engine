package uk.gov.di.gpg45engine.services;

import uk.gov.di.gpg45engine.domain.data.IdentityEvidence;
import uk.gov.di.gpg45engine.domain.gpg45.EvidenceScore;
import uk.gov.di.gpg45engine.domain.gpg45.IdentityProfile;

import java.util.Map;

public interface ProfileMatchingService {

    IdentityProfile matchEvidenceScoringToProfile(EvidenceScore[] evidenceScoreBundle);
}
