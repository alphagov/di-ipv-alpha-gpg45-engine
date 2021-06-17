package uk.gov.di.gpg45engine.domain.dto;

import lombok.Data;
import uk.gov.di.gpg45engine.domain.data.IdentityVerificationBundle;
import uk.gov.di.gpg45engine.domain.gpg45.IdentityProfile;

@Data
public class CalculateResponseDto {
    private final IdentityVerificationBundle identityVerificationBundle;
    private final IdentityProfile matchedIdentityProfile;
}
