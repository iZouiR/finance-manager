package org.izouir.budgetplanningservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@Schema(description = "Limitation")
public class LimitationDto {
    @NotNull(message = "Limitation ID can't be null")
    @Positive(message = "Limitation ID must be greater than 0")
    @Schema(description = "Limitation ID", example = "1")
    private Long id;

    @NotNull(message = "Account ID can't be null")
    @Positive(message = "Account ID must be greater than 0")
    @Schema(description = "Account ID", example = "1")
    private Long accountId;

    @NotNull(message = "Amount can't be null")
    @Positive(message = "Amount must be greater than 0")
    @Schema(description = "Money amount", example = "123.45")
    private BigDecimal amount;

    @NotNull(message = "Limitation start date can't be null")
    @FutureOrPresent(message = "Limitation must start in the future or at the moment")
    @Schema(description = "Limitation start date", example = "2021-03-24 16:34:26.666")
    private Timestamp startDate;

    @NotNull(message = "Limitation end date can't be null")
    @Future(message = "Limitation must end in the future")
    @Schema(description = "Limitation end date", example = "2021-03-24 16:34:26.666")
    private Timestamp endDate;
}
