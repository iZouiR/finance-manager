package org.izouir.budgetplanningservice.service;

import org.izouir.budgetplanningservice.dto.GoalDto;

import java.util.List;

public interface GoalService {
    void create(GoalDto goalDto);

    List<GoalDto> findActualByAccountId(Long accountId);
}
