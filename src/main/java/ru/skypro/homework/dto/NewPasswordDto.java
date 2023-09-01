package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class NewPasswordDto {
    @Min(8)
    @Max(16)
    private String currentPassword;
    @Min(8)
    @Max(16)
    private String newPassword;
}
