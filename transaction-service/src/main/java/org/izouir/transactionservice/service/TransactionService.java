package org.izouir.transactionservice.service;

import org.izouir.transactionservice.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    void create(TransactionDto transactionDto);

    List<TransactionDto> findAllByAccountId(Long accountId);
}
