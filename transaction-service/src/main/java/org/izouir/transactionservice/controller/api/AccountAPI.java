package org.izouir.transactionservice.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.izouir.transactionservice.dto.TransactionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/transaction-api/v1/account")
@Tag(name = "Account management")
public interface AccountAPI {
    @Operation(summary = "Find all transactions by account id")
    @GetMapping("/{id}/transactions")
    ResponseEntity<List<TransactionDto>> findAllTransactionsByAccountId(@PathVariable("id") Long id);
}
