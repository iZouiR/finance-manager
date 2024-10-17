package org.izouir.transactionservice.service;

import org.izouir.transactionservice.dto.IncomeDto;

public interface IncomeService {
    IncomeDto findByTransactionId(Long transactionId);

    void create(IncomeDto incomeDto);
}
