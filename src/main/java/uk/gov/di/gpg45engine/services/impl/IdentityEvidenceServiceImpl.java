package uk.gov.di.gpg45engine.services.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uk.gov.di.gpg45engine.domain.data.IdentityEvidence;
import uk.gov.di.gpg45engine.domain.gpg45.Score;
import uk.gov.di.gpg45engine.services.IdentityEvidenceService;

import java.util.List;

@Service
public class IdentityEvidenceServiceImpl implements IdentityEvidenceService {

    private final List<String> approvedAuthoritativeSources;

    public IdentityEvidenceServiceImpl(
        @Qualifier("approved-auth-sources") List<String> approvedAuthoritativeSources
    ) {
        this.approvedAuthoritativeSources = approvedAuthoritativeSources;
    }

    @Override
    public Score getEvidenceStrength(IdentityEvidence identityEvidence) {
        var score = Score.NOT_AVAILABLE;

        switch (identityEvidence.getType()) {
            case UK_PASSPORT:
                score = Score.FOUR;
                break;
            case ATP_GENERIC_DATA:
                score = Score.ONE;
                break;
        }

        return score;
    }

    @Override
    public Score getEvidenceValidity(IdentityEvidence identityEvidence) {
        var score = Score.NOT_AVAILABLE;

        if (identityEvidence.getValidityChecks() == null) {
            return Score.NOT_AVAILABLE;
        }

        var authoritativeSource = identityEvidence.getValidityChecks().getAuthoritativeSource();

        if (approvedAuthoritativeSources.contains(authoritativeSource)) {
            score = Score.FOUR;
        }

        return score;
    }
}
