package org.izouir.budgetplanningservice.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(final String message) {
        super(message);
    }
}
