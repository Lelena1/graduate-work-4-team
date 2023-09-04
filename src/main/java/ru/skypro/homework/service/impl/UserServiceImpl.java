package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.userCover.UserCover;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.exep.UserNotFoundEx;
import ru.skypro.homework.exep.UserNotUpdatedEx;
import ru.skypro.homework.repository.userCover.UserCoverRepository;
import ru.skypro.homework.repository.users.UsersRepository;
import ru.skypro.homework.service.UserService;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserCoverRepository userCoverRepository;

    @Override

    public NewPasswordDto addNewPassword(String currentPassword, String newPassword) {
        return null;
    }

    @Override
    public UserDto getUser(Integer id) throws UserNotFoundEx {
        User user = usersRepository.findUserById(id);
        if (user == null) {
            throw new UserNotFoundEx("user not found");

        }
        return UserDto.toModel(user);

    }


    @Override
    public User updateUser(Integer id, UpdateUserDto userDto) throws UserNotUpdatedEx {
        User user = usersRepository.findUserById(id);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        return usersRepository.save(user);
    }

    @Value("${user.cover.dir.path}")
    private String fileDir;

    @Override
    public void uploadImage(Integer userId, MultipartFile multipartFile) throws IOException, UserNotFoundEx {
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
        userCoverRepository.save(userCover);

    }
    public  UserCover findCover(Integer id) {
        UserCover userId = userCoverRepository.findByUserId(id);
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
