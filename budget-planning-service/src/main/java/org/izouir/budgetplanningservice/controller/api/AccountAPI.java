package org.izouir.budgetplanningservice.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.izouir.budgetplanningservice.dto.GoalDto;
import org.izouir.budgetplanningservice.dto.LimitationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/budget-planning-api/v1/account")
@Tag(name = "Account management")
public interface AccountAPI {
    @Operation(summary = "Find actual goals by account id")
    @GetMapping("/{id}/goals")
    ResponseEntity<List<GoalDto>> findActualGoalsByAccountId(@PathVariable("id") Long accountId);

    @Operation(summary = "Find actual limitations by account id")
    @GetMapping("/{id}/limitations")
    ResponseEntity<List<LimitationDto>> findActualLimitsByAccountId(@PathVariable("id") Long accountId);
}
