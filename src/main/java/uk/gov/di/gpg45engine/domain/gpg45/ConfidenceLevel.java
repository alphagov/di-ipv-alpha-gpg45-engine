package uk.gov.di.gpg45engine.domain.gpg45;

import com.google.gson.annotations.SerializedName;

public enum ConfidenceLevel {

    @SerializedName("None")
    NONE(0),
    @SerializedName("Low")
    LOW(1),
    @SerializedName("Medium")
    MEDIUM(2),
    @SerializedName("High")
    HIGH(3),
    @SerializedName("VeryHigh")
    VERY_HIGH(4);

    private final int levelOfConfidence;

    ConfidenceLevel(int levelOfConfidence) {
        this.levelOfConfidence = levelOfConfidence;
    }

    public int getValue() {
        return levelOfConfidence;
    }
}
