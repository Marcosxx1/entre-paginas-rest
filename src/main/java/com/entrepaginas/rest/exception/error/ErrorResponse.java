package com.entrepaginas.rest.exception.error;

import static com.entrepaginas.rest.exception.error.CommonErrorTypes.ERROR;

import lombok.*;

@Data
@EqualsAndHashCode
public class ErrorResponse {
    private String type;
    private String title;
    private String detail;

    public ErrorResponse() {}

    public ErrorResponse(String title, String detail) {
        this.type = ERROR;
        this.title = title;
        this.detail = detail;
    }

    public ErrorResponse(String title, String detail, String type) {
        this.type = type;
        this.title = title;
        this.detail = detail;
    }
}
