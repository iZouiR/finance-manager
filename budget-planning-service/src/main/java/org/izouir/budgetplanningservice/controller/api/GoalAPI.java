package org.izouir.budgetplanningservice.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.izouir.budgetplanningservice.dto.GoalDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/budget-planning-api/v1/goal")
@Tag(name = "Goal management")
public interface GoalAPI {
    @Operation(summary = "Goal creation")
    @PostMapping
    ResponseEntity<Void> create(@RequestBody final GoalDto goalDto);
}
