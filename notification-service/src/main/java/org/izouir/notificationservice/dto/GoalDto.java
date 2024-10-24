package org.izouir.notificationservice.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
public class GoalDto {
    @NotNull(message = "Goal ID can't be null")
    @Positive(message = "Goal ID must be greater than 0")
    private Long id;

    @NotNull(message = "Account ID can't be null")
    @Positive(message = "Account ID must be greater than 0")
    private Long accountId;

    @NotNull(message = "Amount can't be null")
    @Positive(message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Goal end date can't be null")
    @Future(message = "Goal must end in the future")
    private Timestamp until;

    @NotNull(message = "Achieved flag can't be null")
    private Boolean achieved;
}
