package uk.gov.di.gpg45engine.matchers;

import uk.gov.di.gpg45engine.domain.gpg45.Score;

public class ScoreMatcher {

    public static boolean equalsOrGreater(Score score, Score score2) {
        return score.getScoreValue() >= score2.getScoreValue();
    }

    public static boolean greater(Score score, Score score2) {
        return score.getScoreValue() >= score2.getScoreValue();
    }
}
