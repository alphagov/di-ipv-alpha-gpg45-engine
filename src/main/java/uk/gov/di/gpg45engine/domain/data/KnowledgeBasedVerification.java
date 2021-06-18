package uk.gov.di.gpg45engine.domain.data;

import lombok.Data;

@Data
public class KnowledgeBasedVerification {
    private Quality quality;
    private String question;
    private String response;
}
