package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.userCover.UserCover;

import java.io.IOException;

public interface UserCoverService {

    public void uploadImage(Integer userId, MultipartFile multipartFile) throws IOException;

    public UserCover findByid(Integer userId);
}
