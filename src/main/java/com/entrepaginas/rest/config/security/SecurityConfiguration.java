package com.entrepaginas.rest.config.security;

import static com.entrepaginas.rest.config.security.Path.USERS_PATH;

import com.entrepaginas.rest.config.security.config.HttpSecurityCustomizer;
import jakarta.annotation.Priority;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private static final String[] OPEN_POST_ENDPOINTS = {USERS_PATH};
    private static final String[] UNSECURED_ENDPOINTS = {"/actuator/"};

    private final HandlerExceptionResolver handlerExceptionResolver;

    @Bean
    @Priority(Ordered.HIGHEST_PRECEDENCE)
    public HttpSecurityCustomizer customHttpSecurity() {
        return http -> http.authorizeHttpRequests(auth -> auth.requestMatchers("/users")
                        .permitAll()
                        .requestMatchers(UNSECURED_ENDPOINTS)
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(Customizer.withDefaults()).authenticationEntryPoint(customAuthEntryPoint()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> {
                    csrf.disable();
                    System.out.println("CSRF protection disabled");
                })
                .cors(Customizer.withDefaults());
    }

    @Bean
    public AuthenticationEntryPoint customAuthEntryPoint() {
        return (request, response, authException) ->
                handlerExceptionResolver.resolveException(request, response, null, authException);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity, List<HttpSecurityCustomizer> httpSecurityConfigurer) throws Exception {
        for (var configurer : httpSecurityConfigurer) {
            configurer.configure(httpSecurity);
        }
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
