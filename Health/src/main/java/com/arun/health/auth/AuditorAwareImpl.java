package com.arun.health.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // get security context
        // get auth
        // get the principal
        // get username
        return Optional.of("Arun Mahajan");
    }
}
