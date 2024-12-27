package com.entrepaginas.rest.config.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@FunctionalInterface
public interface HttpSecurityCustomizer {
    void configure(HttpSecurity httpSecurity) throws Exception;
}
