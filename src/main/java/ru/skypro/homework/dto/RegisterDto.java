package ru.skypro.homework.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.skypro.homework.entity.users.Role;
import ru.skypro.homework.entity.users.User;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RegisterDto extends User {
    @Min(4)
    @Max(32)
    private String userName;
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

    public static RegisterDto toModel(User user) {
        RegisterDto model = new RegisterDto();
        model.setFirstName(user.getFirstName());
        model.setUserName(user.getUsername());
        model.setPassword(user.getPassword());
        model.setLastName(user.getLastName());
        model.setPhone(user.getPhone());
        model.setRole(user.getRole());
        return model;

    }

}
