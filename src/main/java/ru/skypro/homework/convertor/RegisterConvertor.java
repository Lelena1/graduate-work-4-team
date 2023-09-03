package ru.skypro.homework.convertor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.entity.users.Register;

@Component
public class RegisterConvertor {
    private final ModelMapper modelMapper;

    public RegisterConvertor() {
        this.modelMapper = new ModelMapper();
    }

    public RegisterDto converterDTO(Register register) {
        return modelMapper.map(register, RegisterDto.class);
    }

    public Register convertEntity(RegisterDto registerDto) {
        return modelMapper.map(registerDto, Register.class);
    }
}
