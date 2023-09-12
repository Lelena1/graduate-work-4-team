package ru.skypro.homework.service.users;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDto;
import ru.skypro.homework.dto.auth.RegisterDto;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.exceptions.UserNotFoundEx;

public interface UsersService {
    void register(RegisterDto registerDto);

    void uploadImage(Integer userId, MultipartFile multipartFile);


    UpdateUserDto updateUser(Integer id, UpdateUserDto userDto);

    UserDto getUser(Integer id) throws UserNotFoundEx;


    void addNewPassword(NewPasswordDto newPasswordDto);




}
