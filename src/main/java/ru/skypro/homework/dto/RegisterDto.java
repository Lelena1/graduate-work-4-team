package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Data
public class RegisterDto {
    @Min(4)
    @Max(32)
    private String username;
    @Min(8)
    @Max(16)
    private String password;
    @Min(2)
    @Max(16)
    private String firstName;
    @Min(2)
    @Max(16)
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
    private Role role;
}
