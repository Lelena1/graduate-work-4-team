package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.exep.UserAlreadyExist;

public interface AuthService {
    boolean login(String userName, String password);

   boolean register(RegisterDto register);
}
