package org.izouir.transactionservice.controller;

import lombok.RequiredArgsConstructor;
import org.izouir.transactionservice.controller.api.ExpenseAPI;
import org.izouir.transactionservice.dto.ExpenseDto;
import org.izouir.transactionservice.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExpenseController implements ExpenseAPI {
    private final ExpenseService expenseService;

    @Override
    public ResponseEntity<ExpenseDto> findByTransactionId(final Long transactionId) {
        return ResponseEntity.ok(expenseService.findByTransactionId(transactionId));
    }

    @Override
    public ResponseEntity<Void> create(final ExpenseDto expenseDto) {
        expenseService.create(expenseDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
