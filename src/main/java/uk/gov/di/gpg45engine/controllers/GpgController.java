package uk.gov.di.gpg45engine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import uk.gov.di.gpg45engine.domain.dto.CalculateResponseDto;
import uk.gov.di.gpg45engine.domain.dto.VerificationBundleDto;
import uk.gov.di.gpg45engine.services.Gpg45Service;

@Controller("/")
public class GpgController {

    private final Gpg45Service gpg45Service;

    @Autowired
    public GpgController(Gpg45Service gpg45Service) {
        this.gpg45Service = gpg45Service;
    }

    @GetMapping
    public Mono<ServerResponse> welcome(ServerRequest request) {
        return ServerResponse
            .ok()
            .contentType(MediaType.TEXT_PLAIN)
            .body(BodyInserters.fromValue("GPG 45 Calculator"));
    }

    @PostMapping("/calculate")
    public Mono<ResponseEntity<CalculateResponseDto>> calculate(@RequestBody VerificationBundleDto bundle) {
        var data = gpg45Service.calculate(bundle.getIdentityVerificationBundle());
        var response = ResponseEntity.ok()
            .body(data);

        return Mono.just(response);
    }
}
