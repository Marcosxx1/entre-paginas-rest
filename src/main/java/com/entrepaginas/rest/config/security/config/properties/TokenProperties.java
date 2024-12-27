package com.entrepaginas.rest.config.security.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TokenProperties {
    Long expiresIn;
}
