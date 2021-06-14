package uk.gov.di.gpg45engine.domain.data;

import com.google.gson.annotations.SerializedName;

public enum Quality {

    @SerializedName("Low")
    LOW("Low"),

    @SerializedName("Medium")
    MEDIUM("Medium"),

    @SerializedName("High")
    HIGH("High");

    private final String quality;

    private Quality(String quality) {
        this.quality = quality;
    }

    @Override
    public String toString() {
        return quality;
    }
}
