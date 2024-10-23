package org.izouir.transactionservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.izouir.transactionservice.dto.TransactionDto;
import org.izouir.transactionservice.exception.AccountNotFoundException;
import org.izouir.transactionservice.mapper.TransactionMapper;
import org.izouir.transactionservice.repository.AccountRepository;
import org.izouir.transactionservice.repository.TransactionRepository;
import org.izouir.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final KafkaTemplate<String, TransactionDto> kafkaTemplate;
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Value("${spring.kafka.producer.properties.topic-transaction}")
    private String topicTransaction;

    @Override
    public void create(final TransactionDto transactionDto) {
        final var accountId = transactionDto.getAccountId();
        final var account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(
                        String.format("Account with id = %s not found", accountId)));
        final var transaction = TransactionMapper.toEntity(transactionDto, account);
        transactionRepository.save(transaction);
        kafkaTemplate.send(topicTransaction, TransactionMapper.toDto(transaction));
    }

    @Override
    public List<TransactionDto> findAllByAccountId(final Long accountId) {
        final var transactions = transactionRepository.findAllByAccount_Id(accountId);
        return TransactionMapper.toDtoList(transactions);
    }
}
