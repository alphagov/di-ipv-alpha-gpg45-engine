package uk.gov.di.gpg45engine.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.di.gpg45engine.domain.data.IdentityEvidence;
import uk.gov.di.gpg45engine.domain.data.IdentityVerificationBundle;
import uk.gov.di.gpg45engine.domain.gpg45.EvidenceScore;
import uk.gov.di.gpg45engine.domain.gpg45.IdentityProfile;
import uk.gov.di.gpg45engine.domain.gpg45.Score;
import uk.gov.di.gpg45engine.services.Gpg45Service;
import uk.gov.di.gpg45engine.services.IdentityEvidenceService;
import uk.gov.di.gpg45engine.services.ProfileMatchingService;

import java.util.*;

@Service
public class Gpg45ServiceImpl implements Gpg45Service {

    private final IdentityEvidenceService identityEvidenceService;
    private final ProfileMatchingService profileMatchingService;

    @Autowired
    public Gpg45ServiceImpl(
        IdentityEvidenceService identityEvidenceService,
        ProfileMatchingService profileMatchingService
    ) {
        this.identityEvidenceService = identityEvidenceService;
        this.profileMatchingService = profileMatchingService;
    }

    @Override
    public IdentityProfile calculate(IdentityVerificationBundle bundle) {
        var evidenceValidityScores = mapIdentityEvidenceToScore(bundle.getIdentityEvidence());

        return profileMatchingService.matchEvidenceScoringToProfile(evidenceValidityScores.values().toArray(new EvidenceScore[0]));
    }

    private Map<IdentityEvidence, EvidenceScore> mapIdentityEvidenceToScore(IdentityEvidence[] identityEvidence) {
        var validityScoreMap = new HashMap<IdentityEvidence, EvidenceScore>();
        Arrays.stream(identityEvidence).forEach(evidence -> {
            var strength = identityEvidenceService.getEvidenceStrength(evidence);
            var validity = identityEvidenceService.getEvidenceValidity(evidence);

            var evidenceScore = EvidenceScore.builder()
                .strength(strength)
                .validity(validity)
                .activityHistory(Score.NOT_AVAILABLE)
                .identityFraud(Score.NOT_AVAILABLE)
                .verification(Score.NOT_AVAILABLE)
                .build();

            validityScoreMap.put(evidence, evidenceScore);
        });

        return validityScoreMap;
    }
}
