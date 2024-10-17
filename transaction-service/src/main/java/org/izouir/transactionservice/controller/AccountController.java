package org.izouir.transactionservice.controller;

import lombok.RequiredArgsConstructor;
import org.izouir.transactionservice.controller.api.AccountAPI;
import org.izouir.transactionservice.dto.TransactionDto;
import org.izouir.transactionservice.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountAPI {
    private final TransactionService transactionService;

    @Override
    public ResponseEntity<List<TransactionDto>> findAllTransactionsByAccountId(final Long id) {
        return ResponseEntity.ok(transactionService.findAllByAccountId(id));
    }
}
