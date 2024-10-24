package org.izouir.budgetplanningservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@Schema(description = "Transaction")
public class TransactionDto {
    @NotNull(message = "Transaction ID can't be null")
    @Positive(message = "Transaction ID must be greater than 0")
    @Schema(description = "Transaction ID", example = "1")
    private Long id;

    @NotNull(message = "Account ID can't be null")
    @Positive(message = "Account ID must be greater than 0")
    @Schema(description = "Account ID", example = "1")
    private Long accountId;

    @NotNull(message = "Transaction type can't be null")
    @NotBlank(message = "Transaction type can't be blank")
    @Schema(description = "Transaction type", example = "TYPE_INCOME")
    private String type;

    @NotNull(message = "Amount can't be null")
    @Positive(message = "Amount must be greater than 0")
    @Schema(description = "Money amount", example = "123.45")
    private BigDecimal amount;

    @NotNull(message = "Transaction date can't be null")
    @PastOrPresent(message = "Transaction must be placed in the past or at the moment")
    @Schema(description = "Transaction date", example = "2021-03-24 16:34:26.666")
    private Timestamp date;
}
