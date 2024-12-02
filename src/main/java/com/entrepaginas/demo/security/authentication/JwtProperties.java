package com.entrepaginas.demo.security.authentication;

import com.entrepaginas.demo.security.config.properties.TokenProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtProperties {
    private String publicKey;
    private String privateKey;
    private String issuer;
    private Map<String, TokenProperties> tokens;
}
