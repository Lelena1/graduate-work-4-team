package ru.skypro.homework.dto.users;

import lombok.Data;
import ru.skypro.homework.entity.roles.Role;

import javax.validation.constraints.Email;
/**
 * DTO for {@link ru.skypro.homework.dto.users.UserDto}
 */
@Data
public class UserDto {

    //    id пользователя
    private Integer id;
    //    логин пользователя
    @Email
    private String email;
    //    имя пользователя
    private String firstName;
    //    фамилия пользователя
    private String lastName;
    //    телефон пользователя
    private String phone;
    //    роль пользователя
    private String role;
    //    ссылка на аватар пользователя
    private String image;
}
