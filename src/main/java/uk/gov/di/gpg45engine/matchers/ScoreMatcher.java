package uk.gov.di.gpg45engine.matchers;

import uk.gov.di.gpg45engine.domain.gpg45.EvidenceScore;
import uk.gov.di.gpg45engine.domain.gpg45.Score;

import java.util.function.BiPredicate;

public abstract class ScoreMatcher implements BiPredicate<EvidenceScore, EvidenceScore> {

    public static class StrengthMatcher extends ScoreMatcher {
        @Override
        public boolean test(EvidenceScore criteriaScore, EvidenceScore evidenceScore) {
            return evidenceScore.getStrength().getScoreValue() >= criteriaScore.getStrength().getScoreValue();
        }
    }

    public static class ValidityMatcher extends ScoreMatcher {
        @Override
        public boolean test(EvidenceScore criteriaScore, EvidenceScore evidenceScore) {
            return evidenceScore.getValidity().getScoreValue() >= criteriaScore.getValidity().getScoreValue();
        }
    }
}
