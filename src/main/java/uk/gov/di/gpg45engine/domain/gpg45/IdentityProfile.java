package uk.gov.di.gpg45engine.domain.gpg45;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class IdentityProfile {
    @SerializedName("name")
    private IdentityProfileIdentifier identityProfileIdentifier;

    @SerializedName("description")
    private String description;

    @SerializedName("confidence")
    private ConfidenceLevel levelOfConfidence;

    @SerializedName("evidenceScores")
    private EvidenceScore[] evidenceScoreCriteria;
}
