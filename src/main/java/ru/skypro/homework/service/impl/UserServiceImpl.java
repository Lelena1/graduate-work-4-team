package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.exep.UserNotUpdatedEx;
import ru.skypro.homework.repository.users.UsersRepository;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository usersRepository;

    @Override

    public NewPasswordDto addNewPassword(String currentPassword, String newPassword) {
        return null;
    }

    @Override
    public UserDto getUser(Integer id) {
        User user = usersRepository.findUserById(id);
        return UserDto.toModel(user);
    }

    @Override
    public User getOneUser(Integer id) {
        User userEnt = usersRepository.findUserById(id);
        return userEnt;
    }

    @Override
    public UpdateUserDto updateUser(Integer id, UpdateUserDto userDto) throws UserNotUpdatedEx {
        User userById = usersRepository.findUserById(id);
        if (userById == null) {
            throw new UserNotUpdatedEx("user not found");
        }
        return UpdateUserDto.toModel(userById);

    }

    @Override
    public void updateUserImage(int id, MultipartFile multipartFile) {

    }
}
