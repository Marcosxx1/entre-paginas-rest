package com.entrepaginas.demo.security.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TokenProperties {
    Long expiresIn;
}
