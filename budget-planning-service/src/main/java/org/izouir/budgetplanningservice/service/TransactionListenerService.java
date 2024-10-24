package org.izouir.budgetplanningservice.service;

import org.izouir.budgetplanningservice.dto.TransactionDto;

public interface TransactionListenerService {
    void listen(final TransactionDto transactionDto);
}
