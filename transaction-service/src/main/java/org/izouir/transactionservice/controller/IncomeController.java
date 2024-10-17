package org.izouir.transactionservice.controller;

import lombok.RequiredArgsConstructor;
import org.izouir.transactionservice.controller.api.IncomeAPI;
import org.izouir.transactionservice.dto.IncomeDto;
import org.izouir.transactionservice.service.IncomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IncomeController implements IncomeAPI {
    private final IncomeService incomeService;

    @Override
    public ResponseEntity<IncomeDto> findByTransactionId(final Long transactionId) {
        return ResponseEntity.ok(incomeService.findByTransactionId(transactionId));
    }

    @Override
    public ResponseEntity<Void> create(final IncomeDto incomeDto) {
        incomeService.create(incomeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
