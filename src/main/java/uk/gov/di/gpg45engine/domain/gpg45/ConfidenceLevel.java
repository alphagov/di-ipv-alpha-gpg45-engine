package uk.gov.di.gpg45engine.domain.gpg45;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ConfidenceLevel {

    NONE(0, "None"),
    LOW(1, "Low"),
    MEDIUM(2, "Medium"),
    HIGH(3, "High"),
    VERY_HIGH(4, "VeryHigh");

    private final int levelOfConfidence;
    private final String name;

    ConfidenceLevel(int levelOfConfidence, String name) {
        this.levelOfConfidence = levelOfConfidence;
        this.name = name;
    }

    public int getValue() {
        return levelOfConfidence;
    }

    @JsonValue
    @Override
    public String toString() {
        return name;
    }
}
