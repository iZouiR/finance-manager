package org.izouir.transactionservice.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.izouir.transactionservice.dto.ExpenseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/transaction-api/v1/expense")
@Tag(name = "Expense management")
public interface ExpenseAPI {
    @Operation(summary = "Find by transaction id")
    @GetMapping("transaction/{id}")
    ResponseEntity<ExpenseDto> findByTransactionId(@PathVariable("id") Long transactionId);

    @Operation(summary = "Expense creation")
    @PostMapping
    ResponseEntity<Void> create(@RequestBody final ExpenseDto incomeDto);
}
