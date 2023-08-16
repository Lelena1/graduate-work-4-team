package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Data
public class User {
    private Integer id;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private Role role;

}
