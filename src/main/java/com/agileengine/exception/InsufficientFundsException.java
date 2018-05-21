package com.agileengine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(long have, long requested) {
        super("Have: " + have + ", but requested " + requested);
    }
}
