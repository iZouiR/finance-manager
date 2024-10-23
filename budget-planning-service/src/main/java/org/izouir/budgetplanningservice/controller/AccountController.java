package org.izouir.budgetplanningservice.controller;

import lombok.RequiredArgsConstructor;
import org.izouir.budgetplanningservice.controller.api.AccountAPI;
import org.izouir.budgetplanningservice.dto.GoalDto;
import org.izouir.budgetplanningservice.dto.LimitationDto;
import org.izouir.budgetplanningservice.service.GoalService;
import org.izouir.budgetplanningservice.service.LimitationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountAPI {
    private final GoalService goalService;
    private final LimitationService limitationService;

    @Override
    public ResponseEntity<List<GoalDto>> findActualGoalsByAccountId(final Long accountId) {
        return ResponseEntity.ok(goalService.findActualByAccountId(accountId));
    }

    @Override
    public ResponseEntity<List<LimitationDto>> findActualLimitsByAccountId(final Long accountId) {
        return ResponseEntity.ok(limitationService.findActualByAccountId(accountId));
    }
}
