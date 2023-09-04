package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.entity.users.Role;
import ru.skypro.homework.entity.users.User;

@Data
public class UserDto {
    private Integer id;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private Role role;
    private UserCover userCover;
    public static UserDto toModel(User user) {

        UserDto model = new UserDto();
        model.setEmail(user.getEmail());
        model.setId(user.getId());
        model.setPhone(user.getPhone());
        model.setFirstName(user.getFirstName());
        model.setLastName(user.getLastName());
        model.setRole((Role) user.getRole());
        return model;
    }


}
