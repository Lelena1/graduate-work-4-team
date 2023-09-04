package ru.skypro.homework.convertor;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.users.User;
@Component
public class UserConvertor {
    private ModelMapper modelMapper = new ModelMapper();



    public User userConvertor(UserDto userDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        User user = modelMapper.map(userDto, User.class);
        return user;
    }
}
