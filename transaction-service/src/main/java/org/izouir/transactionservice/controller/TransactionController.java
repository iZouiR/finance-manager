package org.izouir.transactionservice.controller;

import lombok.RequiredArgsConstructor;
import org.izouir.transactionservice.controller.api.TransactionAPI;
import org.izouir.transactionservice.dto.TransactionDto;
import org.izouir.transactionservice.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionController implements TransactionAPI {
    private final TransactionService transactionService;

    @Override
    public ResponseEntity<Void> create(final TransactionDto transactionDto) {
        transactionService.create(transactionDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
