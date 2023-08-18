package com.odie.api.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class UserAuditing {
    // Nothing specific to add here, just enabling auditing
}