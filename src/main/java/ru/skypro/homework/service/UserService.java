package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.exep.UserNotUpdatedEx;

import javax.persistence.criteria.CriteriaBuilder;

public interface UserService {

    NewPasswordDto addNewPassword(String currentPassword, String newPassword);

    UserDto getUser(Integer id);

    User getOneUser(Integer id);

    UpdateUserDto updateUser(Integer id, UpdateUserDto userDto) throws UserNotUpdatedEx;

    void updateUserImage(int id, MultipartFile multipartFile);




}
