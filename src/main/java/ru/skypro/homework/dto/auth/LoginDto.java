package ru.skypro.homework.dto.auth;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * DTO for {@link ru.skypro.homework.dto.auth.LoginDto}
 */
@Data
public class LoginDto {
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 4, max = 32, message = "Имя пользователя должно содержать от 4 до 32 символов")
    String username;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 8, max = 16, message = "Пароль должен содержать от 8 до 16 символов")
    String password;
}
