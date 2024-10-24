package org.izouir.notificationservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GoalNotificationDto extends AbstractNotificationDto {
    @Builder
    public GoalNotificationDto(final String email, final String phone, final GoalDto goalDto) {
        super(email, phone);
        this.goal = goalDto;
    }

    @NotNull(message = "Goal can't be null")
    private final GoalDto goal;
}
