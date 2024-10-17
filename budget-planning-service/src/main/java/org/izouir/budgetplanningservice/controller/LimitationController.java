package org.izouir.budgetplanningservice.controller;

import lombok.RequiredArgsConstructor;
import org.izouir.budgetplanningservice.controller.api.LimitationAPI;
import org.izouir.budgetplanningservice.dto.LimitationDto;
import org.izouir.budgetplanningservice.service.LimitationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LimitationController implements LimitationAPI {
    private final LimitationService limitationService;

    @Override
    public ResponseEntity<Void> create(final LimitationDto limitationDto) {
        limitationService.create(limitationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
