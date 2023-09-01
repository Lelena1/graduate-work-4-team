package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private Role role;

}
