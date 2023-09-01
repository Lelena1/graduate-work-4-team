package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

public interface UserService {

    NewPasswordDto addNewPassword(String currentPassword, String newPassword);

    UserDto getUser(UserDto user);

    UpdateUserDto updateUser(UpdateUserDto updateUser);

    void updateUserImage(int id, MultipartFile multipartFile);




}
