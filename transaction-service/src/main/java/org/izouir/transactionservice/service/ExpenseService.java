package org.izouir.transactionservice.service;

import org.izouir.transactionservice.dto.ExpenseDto;

public interface ExpenseService {
    ExpenseDto findByTransactionId(Long transactionId);

    void create(ExpenseDto expenseDto);
}
