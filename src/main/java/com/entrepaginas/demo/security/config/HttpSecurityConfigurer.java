package com.entrepaginas.demo.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@FunctionalInterface
public interface HttpSecurityConfigurer {
    void configure(HttpSecurity httpSecurity) throws Exception;
}
