package uk.gov.di.gpg45engine.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import uk.gov.di.gpg45engine.domain.data.IdentityEvidence;
import uk.gov.di.gpg45engine.domain.gpg45.EvidenceScore;
import uk.gov.di.gpg45engine.domain.gpg45.IdentityProfile;
import uk.gov.di.gpg45engine.services.ProfileMatchingService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ProfileMatchingServiceImpl implements ProfileMatchingService {

    private final List<IdentityProfile> identityProfiles;

    @Autowired
    public ProfileMatchingServiceImpl(
        @Qualifier("identity-profile-list") List<IdentityProfile> identityProfiles
    ) {
        this.identityProfiles = identityProfiles;
    }

    @Override
    public IdentityProfile matchEvidenceScoringToProfile(EvidenceScore[] evidenceScores) {
        var possibleMatches = new ArrayList<IdentityProfile>();

        identityProfiles.forEach(identityProfile -> {

            if (identityProfile.getEvidenceScoreCriteria().length > evidenceScores.length) {
                // skip due to lack of evidence provided.
                return;
            }

            Arrays.stream(evidenceScores).forEach(evidenceScore -> {
                Arrays.stream(identityProfile.getEvidenceScoreCriteria()).forEach(criteria -> {
                    if (evidenceScore.getStrength().getScoreValue() >= criteria.getStrength().getScoreValue()
                        && evidenceScore.getValidity().getScoreValue() >= criteria.getValidity().getScoreValue()
                        && !possibleMatches.contains(identityProfile)) {
                        possibleMatches.add(identityProfile);
                    }
                });
            });

            if (possibleMatches.size() >= identityProfile.getEvidenceScoreCriteria().length) {
                possibleMatches
                    .stream()
                    .peek(x -> log.info("Possible profile: {}", x.getDescription()));
            }
        });

        return null;
    }
}
