package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.convertor.UserConvertor;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.repository.users.UsersRepository;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;

    private final UsersRepository usersRepository;
    private final UserConvertor userConvertor;

    public AuthServiceImpl(UserDetailsManager manager,
                           PasswordEncoder passwordEncoder, UsersRepository usersRepository, UserConvertor userConvertor) {
        this.manager = manager;
        this.encoder = passwordEncoder;
        this.userConvertor = userConvertor;
        ;
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterDto register) {
        if (manager.userExists(register.getUserName())) {
            return false;
        }

        manager.createUser(
                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(register.getPassword())
                        .username(register.getUserName())
                        .roles(register.getRole().name())
                        .build());
        ru.skypro.homework.entity.users.User user = userConvertor.convertEntity(register);
        usersRepository.save(user);

        return true;
    }

}
