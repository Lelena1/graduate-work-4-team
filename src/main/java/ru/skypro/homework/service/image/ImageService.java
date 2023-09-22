package ru.skypro.homework.service.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface ImageService {
    String consumeImageOfGoods(MultipartFile image) throws IOException;

    String consumeImageOfAvatar(MultipartFile image) throws IOException;

    void deleteImageOfGoods(String fullFileName) throws IOException;

    void deleteImageOfAvatars(String fullFileName) throws IOException;

    Path getFullPathToImageOfGoods(String fullFileName);

    Path getFullPathToImageOfAvatars(String fullFileName);

    byte[] imageToByteArray(Path path) throws IOException;
}
