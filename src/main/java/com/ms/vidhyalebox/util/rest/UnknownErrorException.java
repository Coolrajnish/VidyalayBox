package com.ms.vidhyalebox.util.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnknownErrorException extends RuntimeException {
    public UnknownErrorException(String message) {
        super(message);
    }
}