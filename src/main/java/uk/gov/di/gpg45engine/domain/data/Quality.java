package uk.gov.di.gpg45engine.domain.data;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Quality {

    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String quality;

    private Quality(String quality) {
        this.quality = quality;
    }

    @JsonValue
    @Override
    public String toString() {
        return quality;
    }
}
