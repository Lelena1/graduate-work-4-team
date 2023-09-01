package ru.skypro.homework.service.impl;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public NewPassword addNewPassword(String currentPassword, String newPassword) {
        return null;
    }

    @Override
    public User getUser(User user) {
        return null;
    }

    @Override
    public UpdateUser updateUser(UpdateUser updateUser) {
        return null;
    }

    @Override
    public void updateUserImage(int id, MultipartFile multipartFile) {

    }
}
