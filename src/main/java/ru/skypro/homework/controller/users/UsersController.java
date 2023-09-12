package ru.skypro.homework.controller.users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDto;
import ru.skypro.homework.dto.auth.RegisterDto;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.exceptions.PasswordMatches;
import ru.skypro.homework.exceptions.UserNotFoundEx;
import ru.skypro.homework.service.users.UsersService;

import java.io.EOFException;
import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDto registerDto) {
        usersService.register(registerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PatchMapping("/{id}/set_password")

    public ResponseEntity<NewPasswordDto> setPassword(@PathVariable Integer id, @RequestBody NewPasswordDto newPasswordDto
    ) throws UserNotFoundEx, PasswordMatches {
        NewPasswordDto passwordDto = usersService.addNewPassword(id, newPasswordDto);
        return ResponseEntity.ok(passwordDto);

    }

    @GetMapping("/me")

    public ResponseEntity<?>  getUser(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(usersService.getUser(id));
        } catch (UserNotFoundEx e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
    @PatchMapping("/me")

    public ResponseEntity updateUser(@RequestParam Integer id, @RequestBody UpdateUserDto userDto){

        try {
            return ResponseEntity.ok(usersService.updateUser(id, userDto));
        } catch (Exception ex) {
            return   ResponseEntity.badRequest().body("User not updated");
        }
    }

    @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity updateUserImage(@PathVariable Integer id, @RequestBody MultipartFile image ) throws IOException {
        log.info("Аватар пользователя успешно обновлен");


        try {
            usersService.uploadImage(id, image);
            return ResponseEntity.ok()
                    .build();

        } catch (Exception e) {
            throw new EOFException("Не удалось загрузть файл");
        }

    }







}
