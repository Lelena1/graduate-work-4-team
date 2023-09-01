package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UserService {

    NewPassword addNewPassword(String currentPassword, String newPassword);

    User getUser(User user);

    UpdateUser updateUser(UpdateUser updateUser);

    void updateUserImage(int id, MultipartFile multipartFile);




}