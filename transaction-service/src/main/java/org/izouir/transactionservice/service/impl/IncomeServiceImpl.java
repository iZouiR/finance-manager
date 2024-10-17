package org.izouir.transactionservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.izouir.transactionservice.dto.IncomeDto;
import org.izouir.transactionservice.exception.IncomeNotFoundException;
import org.izouir.transactionservice.exception.TransactionNotFoundException;
import org.izouir.transactionservice.mapper.IncomeMapper;
import org.izouir.transactionservice.repository.IncomeRepository;
import org.izouir.transactionservice.repository.TransactionRepository;
import org.izouir.transactionservice.service.IncomeService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public IncomeDto findByTransactionId(final Long transactionId) {
        final var income = incomeRepository.findByTransaction_Id(transactionId)
                .orElseThrow(() -> new IncomeNotFoundException(
                        String.format("Income with transaction id = %s not found", transactionId)));
        return IncomeMapper.toDto(income);
    }

    @Override
    public void create(final IncomeDto incomeDto) {
        final var transactionId = incomeDto.getTransactionId();
        final var transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException(
                        String.format("Transaction with id = %s not found", transactionId)));
        final var income = IncomeMapper.toEntity(incomeDto, transaction);
        incomeRepository.save(income);
    }
}
