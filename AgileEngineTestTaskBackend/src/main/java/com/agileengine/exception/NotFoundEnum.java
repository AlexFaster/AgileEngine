package com.agileengine.exception;

public enum NotFoundEnum {
    WALLET("Wallet"),
    TRANSACTION("Transaction");

    private String identifier;

    NotFoundEnum(String identifier) {
        this.identifier = identifier;
    }

    public String identifier() {
        return identifier;
    }
}
