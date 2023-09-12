package ru.skypro.homework.service.users.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDto;
import ru.skypro.homework.dto.auth.RegisterDto;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.exceptions.UserNotFoundEx;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.repository.users.UsersRepository;
import ru.skypro.homework.service.users.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    public UsersServiceImpl(UsersRepository usersRepository, UserMapper userMapper) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public void register(RegisterDto registerDto) {
        User user = userMapper.toEntity(registerDto);
        usersRepository.save(user);
    }

    @Override
    public UpdateUserDto updateUser(Integer id, UpdateUserDto userDto) {
        User userById = usersRepository.findUserById(id);
        User user = userMapper.toEntity(userDto);
        userById.setPhone(user.getPhone());
        userById.setFirstName(user.getFirstName());
        userById.setLastName(user.getLastName());
        usersRepository.save(user);
        return userMapper.toUpdateUserDto(user);
    }

    @Override
    public UserDto getUser(Integer id) throws UserNotFoundEx {
        User user = usersRepository.findUserById(id);
        if (user == null) {
            throw new UserNotFoundEx("user not found");
        }
        return userMapper.toUserDto(user);
    }

    @Override
    public void addNewPassword(NewPasswordDto newPasswordDto) {

    }

    @Override
    public void uploadImage(Integer userId, MultipartFile multipartFile) {

    }

}
