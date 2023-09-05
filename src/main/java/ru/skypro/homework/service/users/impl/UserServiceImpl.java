package ru.skypro.homework.service.users.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.repository.users.UsersRepository;
import ru.skypro.homework.service.users.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;


    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDto getUser() {
        return null;
    }

    @Override
    public UpdateUserDto updateUser(UpdateUserDto updateUserDto) {
        return null;
    }

    @Override
    public Void updateUserImage(MultipartFile image) {
        return null;
    }
}
