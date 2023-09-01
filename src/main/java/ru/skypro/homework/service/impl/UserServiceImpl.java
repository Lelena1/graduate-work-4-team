package ru.skypro.homework.service.impl;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public NewPasswordDto addNewPassword(String currentPassword, String newPassword) {
        return null;
    }

    @Override
    public UserDto getUser(UserDto user) {
        return null;
    }

    @Override
    public UpdateUserDto updateUser(UpdateUserDto updateUser) {
        return null;
    }

    @Override
    public void updateUserImage(int id, MultipartFile multipartFile) {

    }
}
