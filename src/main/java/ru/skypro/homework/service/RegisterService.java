package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.entity.users.User;

@Service
public interface RegisterService {

    User regiserUser(RegisterDto register);
}
