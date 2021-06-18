package uk.gov.di.gpg45engine.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.di.gpg45engine.domain.data.IdentityEvidence;
import uk.gov.di.gpg45engine.domain.data.IdentityVerificationBundle;
import uk.gov.di.gpg45engine.domain.dto.CalculateResponseDto;
import uk.gov.di.gpg45engine.domain.gpg45.EvidenceScore;
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
    public CalculateResponseDto calculate(IdentityVerificationBundle bundle) {
        mapIdentityEvidenceToScore(bundle.getIdentityEvidence());
        var identityProfile = profileMatchingService.matchEvidenceScoringToProfile(bundle.getIdentityEvidence());

        return new CalculateResponseDto(bundle, identityProfile);
    }

    private void mapIdentityEvidenceToScore(IdentityEvidence[] identityEvidence) {
        Arrays.stream(identityEvidence).forEach(evidence -> {

            if (evidence.getEvidenceScore() != null) {
                // if score is already provided skip scoring step, useful for playing about with values.
                return;
            }

            var strength = identityEvidenceService.getEvidenceStrength(evidence);
            var validity = identityEvidenceService.getEvidenceValidity(evidence);
            var evidenceScore = new EvidenceScore(strength, validity);

            evidence.setEvidenceScore(evidenceScore);
        });
    }
}
