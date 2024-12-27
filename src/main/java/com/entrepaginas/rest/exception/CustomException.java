package com.entrepaginas.rest.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {

    private final String title;
    private final String detail;
    private final HttpStatus httpStatus;

    public CustomException(String title, String detail, HttpStatus httpStatus) {
        super(detail);
        this.title = title;
        this.detail = detail;
        this.httpStatus = httpStatus;
    }

    /*    public CustomException (String title, String detail, HttpStatus httpStatus, String type) {
        super(detail);
        this.title = title;
        this.detail = detail;
        this.httpStatus = httpStatus;
        this.type = type;
    }*/
}
