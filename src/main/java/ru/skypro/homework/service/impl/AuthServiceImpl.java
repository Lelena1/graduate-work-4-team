package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.convertor.RegisterConvertor;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.entity.users.Register;
import ru.skypro.homework.repository.users.RegisterRepository;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final RegisterRepository registerRepository;

    private final RegisterConvertor registerConvertor;

    public AuthServiceImpl(UserDetailsManager manager,
                           PasswordEncoder passwordEncoder, RegisterRepository registerRepository, RegisterConvertor registerConvertor) {
        this.manager = manager;
        this.encoder = passwordEncoder;
        this.registerRepository = registerRepository;
        this.registerConvertor = registerConvertor;
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
        Register user = registerConvertor.convertEntity(register);
        registerRepository.save(user);
        return true;
    }

}
