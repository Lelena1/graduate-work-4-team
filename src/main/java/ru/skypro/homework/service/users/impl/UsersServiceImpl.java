package ru.skypro.homework.service.users.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDto;
import ru.skypro.homework.dto.auth.RegisterDto;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.entity.users.UserCover;
import ru.skypro.homework.exceptions.PasswordMatches;
import ru.skypro.homework.exceptions.UserNotFoundEx;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.repository.users.CoverRepository;
import ru.skypro.homework.repository.users.UsersRepository;
import ru.skypro.homework.service.users.UsersService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    private final CoverRepository coverRepository;

    public UsersServiceImpl(UsersRepository usersRepository, UserMapper userMapper, CoverRepository coverRepository) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
        this.coverRepository = coverRepository;
    }

    @Override
    @Transactional
    public void register(RegisterDto registerDto) {
        User user = userMapper.toEntity(registerDto);
        usersRepository.save(user);
    }

    @Override
    public UpdateUserDto updateUser(Integer id, UpdateUserDto userDto) {
        User userById = usersRepository.findUserById(id);
        User user = userMapper.toEntity(userDto);
        userById.setPhone(user.getPhone());
        userById.setFirstName(user.getFirstName());
        userById.setLastName(user.getLastName());
        return userMapper.toUpdateUserDto(userById);
    }

    @Override
    public UserDto getUser(Integer id) throws UserNotFoundEx {
        User user = usersRepository.findUserById(id);
        if (user == null) {
            throw new UserNotFoundEx("user not found");
        }
        return userMapper.toUserDto(user);
    }

    @Override
    public NewPasswordDto addNewPassword(Integer id,NewPasswordDto newPasswordDto) throws PasswordMatches {
        User userById = usersRepository.findUserById(id);
        User password = userMapper.toEntity(newPasswordDto);
        userById.setPassword(newPasswordDto.getCurrentPassword());
        userById.setPassword(newPasswordDto.getNewPassword());
        // userById.setPassword(password.getPassword());
        if (userById.getPassword().equals(newPasswordDto.getCurrentPassword())) {
            userById.setPassword(newPasswordDto.getNewPassword());
        } else {
            throw new PasswordMatches("password matches");
        }
        usersRepository.save(userById);
        return userMapper.toNewPasswordDto(userById);
    }

    @Value("${user.cover.dir.path}")
    private String fileDir;
    @Override
    public void uploadImage(Integer userId, MultipartFile multipartFile) throws IOException {
        User user = usersRepository.findUserById(userId);
        Path filePath = Path.of(fileDir, user + ".");// + getExtension(Objects.requireNonNull(multipartFile.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = multipartFile.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
            bis.transferTo(bos);
        }
        UserCover userCover = findCover(userId);
        userCover.setUser(user);
        userCover.setFilePath(filePath.toString());
        userCover.setMediaType(multipartFile.getContentType());
        userCover.setFileSize(String.valueOf(multipartFile.getSize()));
        userCover.setPreview(generateImagePreviev(filePath));
        coverRepository.save(userCover);


    }

    public  UserCover findCover(Integer id) {
        UserCover userId = coverRepository.findByUserId(id);
        if (userId == null) {
            return new UserCover();
        }
        return userId;
    }

    private byte[] generateImagePreviev(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExtension(filePath.getFileName().toString()),baos);
            return baos.toByteArray();
        }
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
