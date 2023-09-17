package ru.skypro.homework.dto.users;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * DTO for {@link ru.skypro.homework.dto.users.UpdateUserDto}
 */
@Data
public class UpdateUserDto {
    //    имя пользователя
    @Size(min = 3, max = 10, message = "Имя пользователя должно содержать от 3 до 10 символов")
    private String firstName;
    //    фамилия пользователя
    @Size(min = 3, max = 10, message = "Фамилия пользователя должна содержать от 3 до 10 символов")
    private String lastName;
    //    телефон пользователя
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Номер телефона должен быть в формате +7(123)123-45-67")
    private String phone;
}
