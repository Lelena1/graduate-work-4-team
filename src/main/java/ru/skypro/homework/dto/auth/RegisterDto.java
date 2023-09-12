package ru.skypro.homework.dto.auth;

import lombok.Data;
import ru.skypro.homework.entity.users.Role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/**
 * DTO for {@link ru.skypro.homework.dto.auth.RegisterDto}
 */
@Data
public class RegisterDto {
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 4, max = 32,message = "Логин должен содержать от 4 до 32 символов")
    private String username;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2, max = 16,message = "Имя должно содержать от 2 до 16 символов")
    private String firstName;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2, max = 16,message = "Фамилия должна содержать от 2 до 16 символов")
    @NotEmpty(message = "Поле не может быть пустым")
    private String lastName;
    @NotEmpty(message = "Поле не может быть пустым")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Номер телефона должен быть в формате +7(123)123-45-67")
    private String phone;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 8, max = 16, message = "Пароль должен содержать от 8 до 16 символов")
    private String password;
    private Role role;

}
