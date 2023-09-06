package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.entity.users.User;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
@Data
public class UpdateUserDto {
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")

    String phone;

    @Min(3)
    @Max(10)
    private String firstName;

    @Min(3)
    @Max(10)
    private String lastName;

}
