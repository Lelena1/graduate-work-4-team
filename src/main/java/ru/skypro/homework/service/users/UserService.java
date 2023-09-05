package ru.skypro.homework.service.users;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;

public interface UserService {
    UserDto getUser();
    UpdateUserDto updateUser(UpdateUserDto updateUserDto);
    Void updateUserImage (MultipartFile image);
}
