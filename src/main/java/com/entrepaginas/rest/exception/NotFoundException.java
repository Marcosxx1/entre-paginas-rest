package com.entrepaginas.rest.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException {

    public NotFoundException(String title, String detail) {
        super(title, detail, HttpStatus.NOT_FOUND);
    }
}
