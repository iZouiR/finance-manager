package org.izouir.budgetplanningservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LimitationNotificationDto extends AbstractNotificationDto {
    @Builder
    public LimitationNotificationDto(final String email, final String phone, final LimitationDto limitationDto) {
        super(email, phone);
        this.limitation = limitationDto;
    }

    @NotNull(message = "Limitation can't be null")
    private final LimitationDto limitation;
}
