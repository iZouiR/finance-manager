package org.izouir.budgetplanningservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.izouir.budgetplanningservice.dto.LimitationDto;
import org.izouir.budgetplanningservice.exception.AccountNotFoundException;
import org.izouir.budgetplanningservice.mapper.LimitationMapper;
import org.izouir.budgetplanningservice.repository.AccountRepository;
import org.izouir.budgetplanningservice.repository.LimitationRepository;
import org.izouir.budgetplanningservice.service.LimitationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LimitationServiceImpl implements LimitationService {
    private final AccountRepository accountRepository;
    private final LimitationRepository limitationRepository;

    @Override
    public void create(final LimitationDto limitationDto) {
        final var accountId = limitationDto.getAccountId();
        final var account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(
                        String.format("Account with id = %s not found", accountId)));
        final var limit = LimitationMapper.toEntity(limitationDto, account);
        limitationRepository.save(limit);
    }

    @Override
    public List<LimitationDto> findActualByAccountId(final Long accountId) {
        final var limits = limitationRepository.findActualByAccount_Id(accountId);
        return LimitationMapper.toDtoList(limits);
    }
}
