package org.izouir.transactionservice.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(final String message) {
        super(message);
    }
}
