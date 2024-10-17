package org.izouir.transactionservice.exception;

public class IncomeNotFoundException extends RuntimeException {
    public IncomeNotFoundException(final String message) {
        super(message);
    }
}
