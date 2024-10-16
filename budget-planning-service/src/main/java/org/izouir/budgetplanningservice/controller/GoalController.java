package org.izouir.budgetplanningservice.controller;

import lombok.RequiredArgsConstructor;
import org.izouir.budgetplanningservice.controller.api.GoalAPI;
import org.izouir.budgetplanningservice.dto.GoalDto;
import org.izouir.budgetplanningservice.service.GoalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GoalController implements GoalAPI {
    private final GoalService goalService;

    @Override
    public ResponseEntity<Void> create(final GoalDto goalDto) {
        goalService.create(goalDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
