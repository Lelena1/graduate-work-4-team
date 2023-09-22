package ru.skypro.homework.service.users;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDto;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.entity.users.User;

import java.io.IOException;

public interface UserService {
    void setPassword(NewPasswordDto newPasswordDto);

    UserDto getUser();

    UpdateUserDto updateUser(UpdateUserDto updateUserDto);

    void updateUserImage(MultipartFile image) throws IOException;
//    byte[] updateUserImage(MultipartFile image) throws IOException;

    byte[] getImage(String urlToImage) throws IOException, InterruptedException;

    User getAuthor();
}
