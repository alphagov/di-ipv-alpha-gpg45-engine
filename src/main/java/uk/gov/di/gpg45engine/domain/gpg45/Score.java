package uk.gov.di.gpg45engine.domain.gpg45;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.google.gson.annotations.SerializedName;

public enum Score {

    @JsonEnumDefaultValue
    @SerializedName("0")
    NOT_AVAILABLE(0),

    @SerializedName("1")
    ONE(1),
    @SerializedName("2")
    TWO(2),
    @SerializedName("3")
    THREE(3),
    @SerializedName("4")
    FOUR(4);

    private final int score;

    Score(final int score) {
        this.score = score;
    }

    public int getScoreValue() {
        return score;
    }
}
