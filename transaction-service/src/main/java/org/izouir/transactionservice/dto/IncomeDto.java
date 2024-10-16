package org.izouir.transactionservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Income")
public class IncomeDto {
    @NotNull(message = "Income ID can't be null")
    @Positive(message = "Income ID must be greater than 0")
    @Schema(description = "Income ID", example = "1")
    private Long id;

    @NotNull(message = "Transaction ID can't be null")
    @Positive(message = "Transaction ID must be greater than 0")
    @Schema(description = "Transaction ID", example = "1")
    private Long transactionId;

    @NotNull(message = "Income type can't be null")
    @NotBlank(message = "Income type can't be blank")
    @Schema(description = "Income type", example = "TYPE_SALARY")
    private String type;
}
