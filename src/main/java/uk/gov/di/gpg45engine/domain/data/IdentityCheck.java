package uk.gov.di.gpg45engine.domain.data;

import com.fasterxml.jackson.annotation.JsonValue;

public enum IdentityCheck {

    NONE("None"),
    PUBLISHED_POLICY("PublishedPolicy"),
    ANTI_MONEY_LAUNDERING("AntiMoneyLaundering"),
    PHYSICAL_BIOMETRIC("PhysicalBiometric");

    private final String identityCheck;

    IdentityCheck(String identityCheck) {
        this.identityCheck = identityCheck;
    }

    @JsonValue
    @Override
    public String toString() {
        return identityCheck;
    }
}
