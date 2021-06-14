package uk.gov.di.gpg45engine.domain.data;

import lombok.Data;

@Data
public class IdentityVerification {
    private KnowledgeBasedVerification[] staticKBV;
    private String photoMatch;
    private String biometricMatch;
    private KnowledgeBasedVerification[] dynamicKBV;
}
