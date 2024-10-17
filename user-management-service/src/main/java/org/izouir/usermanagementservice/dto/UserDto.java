package org.izouir.usermanagementservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.izouir.usermanagementservice.entity.UserRole;

@Data
@Builder
@Schema(description = "User")
public class UserDto {
    @NotNull(message = "User ID can't be null")
    @Positive(message = "User ID must be equal or greater than 1")
    @Schema(description = "User ID", example = "1")
    private Long id;

    @Schema(description = "Username", example = "iZouiR")
    @Size(min = 4, max = 16, message = "Username's length must be between 4 and 16")
    @NotBlank(message = "Username can't be blank")
    private String username;

    @Schema(description = "Password", example = "my_1secret1_password")
    @Size(min = 8, max = 24, message = "User password's length must be between 8 and 24")
    @NotBlank(message = "User password can't be blank")
    private String password;

    @Schema(description = "Email", example = "iZouiR@jotaro.ru")
    @Size(min = 5, max = 256, message = "Email's length must be between 5 and 256")
    @NotBlank(message = "Email can't be blank")
    @Email(message = "Email must follow format: user@example.com")
    private String email;

    @Schema(description = "Phone number", example = "80297776655")
    @Size(max = 16, message = "Phone number's length must be lower or equal to 16")
    @NotBlank(message = "Phone number can't be blank")
    private String phone;

    @Schema(description = "User role", example = "ROLE_ADMIN")
    @Size(max = 16, message = "User role's length must lower or equal to 16")
    @NotBlank(message = "User role can't be blank")
    private String role;

    @Schema(description = "Name", example = "Timofey")
    @Size(min = 2, max = 16, message = "Name's length must be between 2 and 16")
    @NotBlank(message = "Name can't be blank")
    private String name;

    @Schema(description = "Surname", example = "Kozlov")
    @Size(min = 2, max = 16, message = "Surname's length must be between 2 and 16")
    @NotBlank(message = "Surname can't be blank")
    private String surname;
}
