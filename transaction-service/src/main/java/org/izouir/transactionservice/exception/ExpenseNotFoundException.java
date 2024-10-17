package org.izouir.transactionservice.exception;

public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException(final String message) {
        super(message);
    }
}
