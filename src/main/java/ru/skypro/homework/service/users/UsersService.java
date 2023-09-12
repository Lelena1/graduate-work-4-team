package ru.skypro.homework.service.users;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDto;
import ru.skypro.homework.dto.auth.RegisterDto;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.exceptions.PasswordMatches;
import ru.skypro.homework.exceptions.UserNotFoundEx;

import java.io.IOException;

public interface UsersService {
    void register(RegisterDto registerDto);

    void uploadImage(Integer userId, MultipartFile multipartFile) throws IOException;


    UpdateUserDto updateUser(Integer id, UpdateUserDto userDto);

    UserDto getUser(Integer id) throws UserNotFoundEx;


    NewPasswordDto addNewPassword(Integer id,NewPasswordDto newPasswordDto) throws PasswordMatches;




}
