package ru.skypro.homework.service.users.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDto;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.entity.users.UserCustom;
import ru.skypro.homework.exceptions.BadRequestException;
import ru.skypro.homework.exceptions.NotFoundException;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.repository.users.UsersRepository;
import ru.skypro.homework.service.image.ImageService;
import ru.skypro.homework.service.users.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserCustomService userCustomService;
    private final UserMapper userMapper;
    private final ImageService imageService;
    private final UsersRepository usersRepository;
    private final HttpServletRequest httpServletRequest;

    public UserServiceImpl(UserCustomService userCustomService,
                           UserMapper userMapper,
                           ImageService imageService,
                           UsersRepository usersRepository, HttpServletRequest httpServletRequest) {
        this.userCustomService = userCustomService;
        this.userMapper = userMapper;
        this.imageService = imageService;
        this.usersRepository = usersRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public void setPassword(NewPasswordDto newPasswordDto) {
        userCustomService.changePassword(newPasswordDto.getCurrentPassword(), newPasswordDto.getNewPassword());
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @Override
    public UserDto getUser() {
        User authorFromAuthentication = getAuthor();
        Integer id = authorFromAuthentication.getId();
        Optional<User> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return userMapper.toUserDto(user);
        }
        throw new NotFoundException("User was not found in database");
    }

    @Override
    public UpdateUserDto updateUser(UpdateUserDto updateUserDto) {
        if (updateUserDto != null) {
            User author = getAuthor();
            userMapper.updateUser(updateUserDto, author);
            UserCustom userCustom = new UserCustom(author);
            userCustomService.updateUser(userCustom);
            return userMapper.toUpdateUserDto(author);
        } else {
            throw new BadRequestException("UpdateUserDto is empty");
        }
    }

    @Override
    @Transactional
    public void updateUserImage(MultipartFile image) throws IOException {
        User author = getAuthor();
        String currentUrlToImage = author.getImage();
        if (currentUrlToImage != null) {
            author.setImage(null);
            usersRepository.save(author);

            String[] split = currentUrlToImage.split("/");
            String imageId = split[split.length - 1];
            imageService.deleteImageOfAvatars(imageId);
        }
        String newUrlToImage = imageService.consumeImageOfAvatar(image);
        author.setImage(newUrlToImage);
        usersRepository.save(author);
    }

//    @Override
//    @Transactional
//    public byte[] updateUserImage(MultipartFile image) throws IOException {
//        User author = getAuthor();
//        String currentUrlToImage = author.getImage();
//        if (currentUrlToImage != null) {
//            author.setImage(null);
//            usersRepository.save(author);
//
//            String[] split = currentUrlToImage.split("/");
//            String fullFileName = split[split.length - 1];
//            imageService.deleteImageOfAvatars(fullFileName);
//        }
//        String newUrlToImage = imageService.consumeImageOfAvatar(image);
//        author.setImage(newUrlToImage);
//        usersRepository.save(author);
//
//        String[] split = newUrlToImage.split("/");
//        String fullFileName = split[split.length - 1];
//
//        Path path = imageService.getFullPathToImageOfAvatars(fullFileName);
//        return imageService.imageToByteArray(path);
//    }

    @Override
    public byte[] getImage(String id) throws IOException {
        if (id == null || id.isEmpty()) {
            throw new NotFoundException("User do not have avatar");
        }
        try {
            Path path = imageService.getFullPathToImageOfAvatars(id);
            return imageService.imageToByteArray(path);
        } catch (NoSuchFileException exception) {
            throw new NotFoundException("Image with avatar not found " + id);
        } catch (IOException exception) {
            throw new IOException("File reading error ", exception);
        }
    }

    @Override
    public User getAuthor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserCustom userCustom = (UserCustom) authentication.getPrincipal();
        return userCustom.getUser();
    }

}