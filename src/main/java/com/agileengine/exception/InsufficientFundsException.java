package com.agileengine.exception;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(long have, long requested) {
        super("Have: " + have + ", but requested " + requested);
    }
}
