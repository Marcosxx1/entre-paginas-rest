package com.entrepaginas.rest.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {

    @Schema(example = "John Doe")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 5, max = 100, message = "Name must be between 5 and 100 characters")
    private String name;

    @Schema(example = "john.doe@example.com")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(example = "012345678900")
    @NotEmpty(message = "cpf cannot be empty")
    @CPF
    private String cpf;

    @Schema(example = "P@ssw0rd!")
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Schema(example = "P@ssw0rd!")
    @NotEmpty(message = "repeatPassword cannot be empty")
    @Size(min = 8, message = "repeatPassword must be at least 8 characters long")
    private String repeatPassword;
}
