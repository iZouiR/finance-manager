package org.izouir.budgetplanningservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.izouir.budgetplanningservice.dto.TransactionDto;
import org.izouir.budgetplanningservice.enums.TransactionType;
import org.izouir.budgetplanningservice.exception.AccountNotFoundException;
import org.izouir.budgetplanningservice.repository.AccountRepository;
import org.izouir.budgetplanningservice.service.GoalService;
import org.izouir.budgetplanningservice.service.LimitationService;
import org.izouir.budgetplanningservice.service.TransactionListenerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionListenerServiceImpl implements TransactionListenerService {
    private final GoalService goalService;
    private final LimitationService limitationService;
    private final AccountRepository accountRepository;

    @Override
    @KafkaListener(topics = "${spring.kafka.consumer.properties.topic-transaction}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "transactionListener")
    public void listen(final TransactionDto transactionDto) {
        final var accountId = transactionDto.getAccountId();

        switch (TransactionType.valueOf(transactionDto.getType())) {
            case TYPE_INCOME -> {
                final var account = accountRepository.findById(accountId)
                        .orElseThrow(() -> new AccountNotFoundException(
                                String.format("Account with id = %s not found", accountId)));

                final var actualGoals = goalService.findActualByAccountId(accountId);
                for (final var goal : actualGoals) {
                    if (account.getBalance().compareTo(goal.getAmount()) >= 0) {
                        // TODO: redirect to notification service
                        System.out.printf("Goal with id %s completed\n", goal.getId());
                    }
                }
            }

            case TYPE_EXPENSE -> {
                final var actualLimitations = limitationService.findActualByAccountId(accountId);
                for (final var limitation : actualLimitations) {
                    if (limitation.getAmount().compareTo(limitation.getSpent()) <= 0) {
                        // TODO: redirect to notification service
                        System.out.printf("Limitation with id %s reached\n", limitation.getId());
                    }
                }
            }
        }
    }
}
