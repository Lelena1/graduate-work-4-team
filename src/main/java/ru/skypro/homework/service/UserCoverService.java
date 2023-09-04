package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.userCover.UserCover;
import ru.skypro.homework.exep.UserNotFoundEx;

import java.io.IOException;

public interface UserCoverService {

    public void uploadImage(Integer userId, MultipartFile multipartFile) throws IOException, UserNotFoundEx;

    public UserCover findByid(Integer userId);
}
