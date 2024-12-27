package com.entrepaginas.rest.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class RegistrationResponse {

    @Schema(example = "1", description = "User ID")
    private Long id;
}
