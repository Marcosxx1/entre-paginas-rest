package com.entrepaginas.rest.exception.error;

import static com.entrepaginas.rest.exception.error.CommonErrorTypes.VALIDATION_ERROR;

import java.util.HashMap;
import java.util.Map;
import lombok.*;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ValidationErrorResponse extends ErrorResponse {

    private Map<String, String> params = new HashMap<>();

    public ValidationErrorResponse(String title, String detail, Map<String, String> params) {
        super(title, detail, VALIDATION_ERROR);
        this.params = params;
    }
}
