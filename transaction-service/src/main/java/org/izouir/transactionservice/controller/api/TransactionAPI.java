package org.izouir.transactionservice.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.izouir.transactionservice.dto.TransactionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/transaction-api/v1/transaction")
@Tag(name = "Transaction management")
public interface TransactionAPI {
    @Operation(summary = "Transaction creation")
    @PostMapping
    ResponseEntity<Void> create(@RequestBody final TransactionDto transactionDto);
}
