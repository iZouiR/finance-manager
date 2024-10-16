package org.izouir.budgetplanningservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.izouir.budgetplanningservice.dto.GoalDto;
import org.izouir.budgetplanningservice.exception.AccountNotFoundException;
import org.izouir.budgetplanningservice.mapper.GoalMapper;
import org.izouir.budgetplanningservice.repository.AccountRepository;
import org.izouir.budgetplanningservice.repository.GoalRepository;
import org.izouir.budgetplanningservice.service.GoalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {
    private final AccountRepository accountRepository;
    private final GoalRepository goalRepository;

    @Override
    public void create(final GoalDto goalDto) {
        final var accountId = goalDto.getAccountId();
        final var account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(
                        String.format("Account with id = %s not found", accountId)));
        final var goal = GoalMapper.toEntity(goalDto, account);
        goalRepository.save(goal);
    }

    @Override
    public List<GoalDto> findActualByAccountId(final Long accountId) {
        final var goals = goalRepository.findActualByAccount_Id(accountId);
        return GoalMapper.toDtoList(goals);
    }
}
