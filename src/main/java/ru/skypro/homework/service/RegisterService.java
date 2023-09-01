package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
@Service
public interface RegisterService {

    RegisterDto regiserUser(RegisterDto register);
}
