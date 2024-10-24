package org.izouir.notificationservice.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
public class LimitationDto {
    @NotNull(message = "Limitation ID can't be null")
    @Positive(message = "Limitation ID must be greater than 0")
    private Long id;

    @NotNull(message = "Account ID can't be null")
    @Positive(message = "Account ID must be greater than 0")
    private Long accountId;

    @NotNull(message = "Amount can't be null")
    @Positive(message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Spent can't be null")
    @PositiveOrZero(message = "Spent must be greater than or equal to 0")
    private BigDecimal spent;

    @NotNull(message = "Limitation start date can't be null")
    @FutureOrPresent(message = "Limitation must start in the future or at the moment")
    private Timestamp startDate;

    @NotNull(message = "Limitation end date can't be null")
    @Future(message = "Limitation must end in the future")
    private Timestamp endDate;

    @NotNull(message = "Reached flag can't be null")
    private Boolean reached;
}
