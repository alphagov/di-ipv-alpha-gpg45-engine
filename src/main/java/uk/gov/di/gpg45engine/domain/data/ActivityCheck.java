package uk.gov.di.gpg45engine.domain.data;

import lombok.Data;

import java.time.Instant;

@Data
public class ActivityCheck {
    private ActivityCheckSource source;
    private Instant[] dates;

    @Data
    private static class ActivityCheckSource {
        private String type;
        private IdentityCheck identityCheck;
    }
}
