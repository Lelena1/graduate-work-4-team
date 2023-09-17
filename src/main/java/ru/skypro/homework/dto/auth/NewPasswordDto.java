package ru.skypro.homework.dto.auth;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * DTO for {@link ru.skypro.homework.dto.auth.NewPasswordDto}
 */
@Data
public class NewPasswordDto {
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 8, max = 16, message = "Пароль должен содержать от 8 до 16 символов")
    private String currentPassword;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 8, max = 16, message = "Пароль должен содержать от 8 до 16 символов")
    private String newPassword;
}
