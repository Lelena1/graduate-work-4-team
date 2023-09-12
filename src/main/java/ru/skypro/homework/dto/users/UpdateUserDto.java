package ru.skypro.homework.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/**
 * DTO for {@link ru.skypro.homework.dto.users.UpdateUserDto}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {

    //    имя пользователя
    @Size(min = 3, max = 10,message = "Имя должно содержать от 3 до 10 символов")
    @Max(10)
    private String firstName;
    //    фамилия пользователя
    @Size(min = 3, max = 10,message = "Фамилия должна содержать от 3 до 10 символов")
    private String lastName;
    //    телефон пользователя
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Телефон вводится в формате:+7(123)12-34-567")
    private String phone;
}
