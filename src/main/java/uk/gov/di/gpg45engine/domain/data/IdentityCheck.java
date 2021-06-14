package uk.gov.di.gpg45engine.domain.data;

import com.google.gson.annotations.SerializedName;

public enum IdentityCheck {

    @SerializedName("None")
    NONE("None"),

    @SerializedName("PublishedPolicy")
    PUBLISHED_POLICY("PublishedPolicy"),

    @SerializedName("AntiMoneyLaundering")
    ANTI_MONEY_LAUNDERING("AntiMoneyLaundering"),

    @SerializedName("PhysicalBiometric")
    PHYSICAL_BIOMETRIC("PhysicalBiometric");

    private final String identityCheck;

    IdentityCheck(String identityCheck) {
        this.identityCheck = identityCheck;
    }

    @Override
    public String toString() {
        return identityCheck;
    }
}
