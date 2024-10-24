package org.izouir.budgetplanningservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.izouir.budgetplanningservice.dto.GoalNotificationDto;
import org.izouir.budgetplanningservice.dto.LimitationNotificationDto;
import org.izouir.budgetplanningservice.dto.TransactionDto;
import org.izouir.budgetplanningservice.enums.TransactionType;
import org.izouir.budgetplanningservice.exception.AccountNotFoundException;
import org.izouir.budgetplanningservice.repository.AccountRepository;
import org.izouir.budgetplanningservice.service.GoalService;
import org.izouir.budgetplanningservice.service.LimitationService;
import org.izouir.budgetplanningservice.service.TransactionListenerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionListenerServiceImpl implements TransactionListenerService {
    private final KafkaTemplate<String, GoalNotificationDto> goalNotificationKafkaTemplate;
    private final KafkaTemplate<String, LimitationNotificationDto> limitationNotificationKafkaTemplate;
    private final GoalService goalService;
    private final LimitationService limitationService;
    private final AccountRepository accountRepository;

    @Value("${spring.kafka.producer.properties.topic-goal}")
    private String topicGoal;

    @Value("${spring.kafka.producer.properties.topic-limitation}")
    private String topicLimitation;

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
                        // TODO: fetch email, phone from user account
                        final var goalNotificationDto = GoalNotificationDto.builder()
                                .email("STUB")
                                .phone("STUB")
                                .goalDto(goal)
                                .build();
                        goalNotificationKafkaTemplate.send(topicGoal, goalNotificationDto);
                    }
                }
            }

            case TYPE_EXPENSE -> {
                final var actualLimitations = limitationService.findActualByAccountId(accountId);
                for (final var limitation : actualLimitations) {
                    if (limitation.getAmount().compareTo(limitation.getSpent()) <= 0) {
                        // TODO: fetch email, phone from user account
                        final var limitationNotificationDto = LimitationNotificationDto.builder()
                                .email("STUB")
                                .phone("STUB")
                                .limitationDto(limitation)
                                .build();
                        limitationNotificationKafkaTemplate.send(topicLimitation, limitationNotificationDto);
                    }
                }
            }
        }
    }
}
