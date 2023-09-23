package ru.skypro.homework.controller.users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDto;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.service.image.ImageService;
import ru.skypro.homework.service.users.impl.UserServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Tag(name = "Users")
@RestController
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@Validated
public class UserController {

    private final UserServiceImpl userService;

    private final ImageService imageService;

    public UserController(UserServiceImpl userService, ImageService imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }

    @PostMapping("/users/set_password")
    @Operation(summary = "Set new password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ОK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    public ResponseEntity<Void> setPassword(
            @Parameter(description = "DTO with current and new password", required = true, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = NewPasswordDto.class))})
            @RequestBody NewPasswordDto newPasswordDto) {
        log.info("setPassword() Starting password change");
        userService.setPassword(newPasswordDto);
        log.info("setPassword() New password set");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/me")
    @Operation(summary = "Get user details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ОK", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<UserDto> getUser() {

        log.info("getUser() Starting getting user details");
        UserDto currentUserDto = userService.getUser();
        if (currentUserDto == null) {
            return ResponseEntity.notFound().build();
        }
        log.info("getUser() Got user details successfully {}", currentUserDto);
        return ResponseEntity.ok(currentUserDto);
    }

    @PatchMapping("/users/me")
    @Operation(summary = "Update user details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "ОK", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UpdateUserDto.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<UpdateUserDto> updateUser(
            @Parameter(description = "DTO with new user data", required = true, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UpdateUserDto.class))})
            @RequestBody UpdateUserDto updateUserDto) {
        log.info("updateUser() Starting user update");
        UpdateUserDto newUser = userService.updateUser(updateUserDto);
        log.info("updateUser() User updated successfully");
        return ResponseEntity.ok(newUser);
    }

    @PatchMapping(value = "/users/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Change user's avatar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "OK", content = @Content),
            @ApiResponse(responseCode = "401",description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<?> updateUserImage(
            @Parameter(required = true, content = {@Content(mediaType = "multipart/form-data", schema = @Schema(type = "string", format = "binary"))})
            @RequestBody MultipartFile image
    ) throws IOException {
        log.info("updateUserImage() Starting avatar update");
        userService.updateUserImage(image);
        log.info("updateUserImage() Avatar was successfully changed to new");
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/images/avatars/{id}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Get user's avatar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "OK", content = {@Content(mediaType = "application/octet-stream", schema = @Schema(type = "string", format = "binary"))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public ResponseEntity<byte[]> getImage(
            @Parameter(name = "id", description = "Id of the image", required = true)
            @PathVariable ("id") String id
    ) throws IOException {
        log.info("getImage() Starting getting avatar with id {} ", id);
        byte[] imageBytes = userService.getImage(id);
        Path path = imageService.getFullPathToImageOfAvatars(id);

        long fileSize = Files.size(path);
        String mediaType = Files.probeContentType(path);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(mediaType));
        httpHeaders.setContentLength(fileSize);

        log.info("getImage() Avatar got successfully");
        return new ResponseEntity<>(imageBytes, httpHeaders, HttpStatus.OK);
    }

}
