package uk.gov.di.gpg45engine.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uk.gov.di.gpg45engine.domain.data.IdentityEvidence;
import uk.gov.di.gpg45engine.domain.gpg45.EvidenceScore;
import uk.gov.di.gpg45engine.domain.gpg45.IdentityProfile;
import uk.gov.di.gpg45engine.matchers.ScoreMatcher;
import uk.gov.di.gpg45engine.services.ProfileMatchingService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ProfileMatchingServiceImpl implements ProfileMatchingService {

    private final List<IdentityProfile> allIdentityProfiles;

    @Autowired
    public ProfileMatchingServiceImpl(
        @Qualifier("identity-profile-list") List<IdentityProfile> allIdentityProfiles
    ) {
        this.allIdentityProfiles = allIdentityProfiles;
    }

    @Override
    public IdentityProfile matchEvidenceScoringToProfile(Map<IdentityEvidence, EvidenceScore> evidenceScoreBundle) {
        /*
            TODO: Compare the evidence bundle scores against the identity profile scores required for that profile.
                  Return the identity profile that matched.
         */

        // note: This is a terrible way to match profiles.
        // the matching service needs a thought about how to process multiple evidence against the same profiles
        // over and over many times without getting into nested streams/loops.
        var evidenceScores = new ArrayList<>(evidenceScoreBundle.values());
        var possibleProfiles = getPossibleProfilesWithMatcher(allIdentityProfiles, evidenceScores, new ScoreMatcher.StrengthMatcher());

        return getPossibleProfilesWithMatcher(possibleProfiles, evidenceScores, new ScoreMatcher.ValidityMatcher())
            .stream()
            // Grab the first highest level of confidence
            .reduce((a, b) -> a.getLevelOfConfidence().getValue() > b.getLevelOfConfidence().getValue() ? a : b)
            .orElse(null);
    }

    private List<IdentityProfile> getPossibleProfilesWithMatcher(List<IdentityProfile> identityProfiles, List<EvidenceScore> evidenceScores, ScoreMatcher scoreMatcher) {
        var possibleProfiles = new ArrayList<IdentityProfile>();

        // note: this needs good old refactoring and breaking out of streams within streams within streams.
        identityProfiles.forEach(identityProfile -> {

            if (identityProfile.getEvidenceScoreCriteria().length > evidenceScores.size()) {
                // if amount of required evidence is greater than provided evidence,
                // we don't match this profile, skip.
                return;
            }

            Arrays.stream(identityProfile.getEvidenceScoreCriteria()).forEach(criteria -> {
                evidenceScores.forEach(evidenceScore -> {
                    if (scoreMatcher.test(criteria, evidenceScore)) {
                        possibleProfiles.add(identityProfile);
                    }
                });
            });
        });

        return possibleProfiles;
    }
}
