package org.izouir.budgetplanningservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@Schema(description = "Goal")
public class GoalDto {
    @NotNull(message = "Goal ID can't be null")
    @Positive(message = "Goal ID must be greater than 0")
    @Schema(description = "Goal ID", example = "1")
    private Long id;

    @NotNull(message = "Account ID can't be null")
    @Positive(message = "Account ID must be greater than 0")
    @Schema(description = "Account ID", example = "1")
    private Long accountId;

    @NotNull(message = "Amount can't be null")
    @Positive(message = "Amount must be greater than 0")
    @Schema(description = "Money amount", example = "123.45")
    private BigDecimal amount;

    @NotNull(message = "Goal end date can't be null")
    @Future(message = "Goal must end in the future")
    @Schema(description = "Goal end date", example = "2021-03-24 16:34:26.666")
    private Timestamp until;

    @NotNull(message = "Achieved flag can't be null")
    @Schema(description = "Achieved flag", example = "true")
    private Boolean achieved;
}
