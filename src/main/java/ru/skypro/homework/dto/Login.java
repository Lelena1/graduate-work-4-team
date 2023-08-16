package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class Login {
    @Min(4)
    @Max(32)
    private String username;
    @Min(8)
    @Max(16)
    private String password;

}
