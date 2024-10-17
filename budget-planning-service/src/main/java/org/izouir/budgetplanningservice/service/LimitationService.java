package org.izouir.budgetplanningservice.service;

import org.izouir.budgetplanningservice.dto.LimitationDto;

import java.util.List;

public interface LimitationService {
    void create(LimitationDto limitationDto);

    List<LimitationDto> findActualByAccountId(Long accountId);
}
