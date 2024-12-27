package com.entrepaginas.rest.config.security.authentication;

import com.entrepaginas.rest.config.security.config.properties.TokenProperties;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
