package org.izouir.budgetplanningservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AbstractNotificationDto {
    @NotBlank(message = "Email can't be blank")
    @Email(message = "Email must follow the format: user@example.com")
    private String email;

    @NotBlank(message = "Phone can't be blank")
    private String phone;
}
