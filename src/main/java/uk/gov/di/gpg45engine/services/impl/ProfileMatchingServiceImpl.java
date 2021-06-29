package uk.gov.di.gpg45engine.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uk.gov.di.gpg45engine.domain.data.IdentityEvidence;
import uk.gov.di.gpg45engine.domain.data.IdentityVerificationBundle;
import uk.gov.di.gpg45engine.domain.gpg45.EvidenceScore;
import uk.gov.di.gpg45engine.domain.gpg45.IdentityProfile;
import uk.gov.di.gpg45engine.matchers.ScoreMatcher;
import uk.gov.di.gpg45engine.services.ProfileMatchingService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public IdentityProfile matchBundleToProfile(IdentityVerificationBundle bundle) {
        var possibleMatches = new ArrayList<IdentityProfile>();

        var evidenceBundle = bundle.getIdentityEvidence();
        var evidenceScores = Arrays.stream(evidenceBundle)
            .map(IdentityEvidence::getEvidenceScore)
            .collect(Collectors.toList());

        identityProfiles
            .stream()
            .takeWhile(_unused -> possibleMatches.size() != 1)
            .forEach(identityProfile -> {

                if (identityProfile.getEvidenceScoreCriteria().length > evidenceScores.size()) {
                    log.debug("Skipped profile (criteria requires more evidence than provided): {}", identityProfile.getDescription());
                    // skip due to lack of evidence provided.
                    return;
                }

                // Compare the other 3 things to see if they match or not.
//                if (ScoreMatcher.equalsOrGreater(evidenceBundle))


                var matchedEvidences = new ArrayList<EvidenceScore>();
                var matchedCriteria = new ArrayList<EvidenceScore>();

                log.debug("Comparing current profile: {}", identityProfile.getDescription());

                Arrays.stream(identityProfile.getEvidenceScoreCriteria()).forEach(criteria ->
                    evidenceScores.forEach(evidenceScore -> {
                        if (ScoreMatcher.equalsOrGreater(evidenceScore.getStrength(), criteria.getStrength())
                            && ScoreMatcher.equalsOrGreater(evidenceScore.getValidity(), criteria.getValidity())
                            && !matchedEvidences.contains(evidenceScore)
                            && !matchedCriteria.contains(criteria)) {
                            log.debug("Matched a piece of evidence from profile {}: ", identityProfile.getDescription());

                            matchedEvidences.add(evidenceScore);
                            matchedCriteria.add(criteria);
                        }
                    })
                );

                if (matchedEvidences.size() >= identityProfile.getEvidenceScoreCriteria().length) {
                    possibleMatches.add(identityProfile);
                }
            });

        if (possibleMatches.size() == 0) {
            log.warn("Couldn't match a profile to provided evidence.");
            return null;
        }

        // Get the first match (which is the highest profile as profiles are ordered).
        var possibleProfileMatch = possibleMatches.get(0);

        log.info("Possible profile matched: {}", possibleProfileMatch.getDescription());
        return possibleProfileMatch;
    }
}
