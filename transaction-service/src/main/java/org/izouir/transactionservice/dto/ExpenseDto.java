package org.izouir.transactionservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Expense")
public class ExpenseDto {
    @NotNull(message = "Expense ID can't be null")
    @Positive(message = "Expense ID must be greater than 0")
    @Schema(description = "Expense ID", example = "1")
    private Long id;

    @NotNull(message = "Transaction ID can't be null")
    @Positive(message = "Transaction ID must be greater than 0")
    @Schema(description = "Transaction ID", example = "1")
    private Long transactionId;

    @NotNull(message = "Expense category can't be null")
    @NotBlank(message = "Expense category can't be blank")
    @Schema(description = "Expense category", example = "CATEGORY_FOOD")
    private String category;
}
