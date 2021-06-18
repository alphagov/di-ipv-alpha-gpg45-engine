package uk.gov.di.gpg45engine.domain.data;

import lombok.Data;

@Data
public class FraudCheck {
    private String level;
    private String[] fraudIndicators;
}
