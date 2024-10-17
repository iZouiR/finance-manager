package org.izouir.transactionservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.izouir.transactionservice.dto.ExpenseDto;
import org.izouir.transactionservice.exception.ExpenseNotFoundException;
import org.izouir.transactionservice.exception.TransactionNotFoundException;
import org.izouir.transactionservice.mapper.ExpenseMapper;
import org.izouir.transactionservice.repository.ExpenseRepository;
import org.izouir.transactionservice.repository.TransactionRepository;
import org.izouir.transactionservice.service.ExpenseService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public ExpenseDto findByTransactionId(final Long transactionId) {
        final var expense = expenseRepository.findByTransaction_Id(transactionId)
                .orElseThrow(() -> new ExpenseNotFoundException(
                        String.format("Expense with transaction id = %s not found", transactionId)));
        return ExpenseMapper.toDto(expense);
    }

    @Override
    public void create(final ExpenseDto expenseDto) {
        final var transactionId = expenseDto.getTransactionId();
        final var transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException(
                        String.format("Transaction with id = %s not found", transactionId)));
        final var expense = ExpenseMapper.toEntity(expenseDto, transaction);
        expenseRepository.save(expense);
    }
}
