package uk.gov.di.gpg45engine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AuthoritativeSourceConfig {

    @Bean("approved-auth-sources")
    List<String> approvedAuthoritativeSources() {
        var sources = new ArrayList<String>();
        sources.add("urn:di:ipv:atp-dcs");

        return sources;
    }
}
