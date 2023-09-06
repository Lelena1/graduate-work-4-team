package ru.skypro.homework.convertor;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.entity.users.User;

@Component
public class RegisterConvertor {

    private ModelMapper modelMapper = new ModelMapper();

    public User convertEntity(RegisterDto registerDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        User user = modelMapper.map(registerDto, User.class);
        return user;
    }
}
