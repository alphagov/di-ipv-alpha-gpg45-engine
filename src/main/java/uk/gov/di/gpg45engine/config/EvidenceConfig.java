package uk.gov.di.gpg45engine.config;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import uk.gov.di.gpg45engine.domain.gpg45.IdentityProfile;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Configuration
public class EvidenceConfig {

    private final Gson gson;

    @Autowired
    public EvidenceConfig() {
        gson = new Gson();
    }

    @Bean("identity-profile-list")
    public List<IdentityProfile> identityProfiles() throws IOException {
        var identityProfileList = new LinkedList<IdentityProfile>();

        var cl = this.getClass().getClassLoader();
        var resolver = new PathMatchingResourcePatternResolver(cl);
        var resources = resolver.getResources("classpath*:profiles/**/*.json");

        for (var resource : resources){
            var file = resource.getFile();
            try (var is = new FileInputStream(file); var isr = new InputStreamReader(is); var reader = new BufferedReader(isr)) {
                var identityProfile = gson.fromJson(reader, IdentityProfile.class);
                identityProfileList.add(identityProfile);
            }
        }

        return identityProfileList;
    }
}
