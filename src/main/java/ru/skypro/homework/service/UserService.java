package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.exep.PasswordMatches;
import ru.skypro.homework.exep.UserNotFoundEx;
import ru.skypro.homework.exep.UserNotUpdatedEx;

import java.io.IOException;

public interface UserService {

    User addNewPassword(Integer id, NewPasswordDto newPassword) throws PasswordMatches, UserNotFoundEx;

    UserDto getUser(Integer id) throws UserNotFoundEx;


    User updateUser(Integer id, UpdateUserDto userDto) throws UserNotUpdatedEx;



    void uploadImage(Integer userId, MultipartFile multipartFile) throws IOException, UserNotFoundEx;
}
