package com.entrepaginas.rest.controller;

import static com.entrepaginas.rest.constant.PathMappings.USER_PATH;
import static com.entrepaginas.rest.constant.SwaggerSchemas.BAD_REQUEST_REGISTER_USER_ERROR;
import static com.entrepaginas.rest.doc.SwaggerExampleRef.INTERNAL_SERVER_ERROR;

import com.entrepaginas.rest.domain.dto.RegistrationResponse;
import com.entrepaginas.rest.domain.dto.UserRegistrationRequest;
import com.entrepaginas.rest.exception.error.ErrorResponse;
import com.entrepaginas.rest.exception.error.ValidationErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Users")
@RequestMapping(
        value = USER_PATH,
        produces = {MediaType.APPLICATION_JSON_VALUE})
// @PreAuthorize("!hasAuthority('?')") Validar
public interface UserController {

    @Operation(
            summary = "Create new user",
            description = "Register a new user",
            responses = {
                @ApiResponse(
                        description = "Validation error",
                        responseCode = "400",
                        content =
                                @Content(
                                        schema = @Schema(implementation = ValidationErrorResponse.class),
                                        examples =
                                                @ExampleObject(
                                                        name = "invalid_arguments",
                                                        ref = BAD_REQUEST_REGISTER_USER_ERROR))),
                @ApiResponse(
                        description = "Internal server error",
                        responseCode = "500",
                        content =
                                @Content(
                                        schema = @Schema(implementation = ErrorResponse.class),
                                        examples =
                                                @ExampleObject(
                                                        name = "internal_server_error",
                                                        ref = INTERNAL_SERVER_ERROR)))
            })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RegistrationResponse registerUser(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest);
}
