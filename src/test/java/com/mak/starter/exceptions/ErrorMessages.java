package com.mak.starter.exceptions;

public enum ErrorMessages {

    ACCOUNT_NOT_FOUND("Account not found"),
    TRANSACTION_NOT_FOUND("Transaction not found"),
    BAD_REQUEST("Bad request"),
    CONSTRAINT_VIOLATION_EXCEPTION("Constraint violation exception")
    ;

    private String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
