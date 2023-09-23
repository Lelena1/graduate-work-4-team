package ru.skypro.homework.service.image.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exceptions.NotFoundException;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.service.image.ImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${image.download.path.goods}")
    private String pathToImagesOfGoods;

    @Value("${image.download.path.avatars}")
    private String pathToImagesOfAvatars;


    private final CommentMapper commentMapper;

    public ImageServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public String consumeImageOfGoods(MultipartFile image) throws IOException {
        String originalFilename = image.getOriginalFilename();
        assert originalFilename != null;
        String[] splittedOriginalFileName = originalFilename.split("\\.");
        String fileName = splittedOriginalFileName[0] + commentMapper.mapDateToLong(LocalDateTime.now());
        String fileExtension = splittedOriginalFileName[1];
        String fullFileName = fileName + "." + fileExtension;
        Path path = Paths.get(pathToImagesOfGoods, fullFileName);
        image.transferTo(path);
        String ROOT_TO_IMAGES_OF_GOODS = "/images/goods/";
        return ROOT_TO_IMAGES_OF_GOODS + fullFileName;
    }

    @Override
    public void deleteImageOfGoods(String fullFileName) throws IOException {
        Path path = getFullPathToImageOfGoods(fullFileName);
        Files.deleteIfExists(path);
    }

    @Override
    public String consumeImageOfAvatar(MultipartFile image) throws IOException {
        String originalFilename = image.getOriginalFilename();
        assert originalFilename != null;
        String[] splittedOriginalFileName = originalFilename.split("\\.");
        String fileName = splittedOriginalFileName[0] + commentMapper.mapDateToLong(LocalDateTime.now());
        String fileExtension = splittedOriginalFileName[1];
        String fullFileName = fileName + "." + fileExtension;
        Path path = Paths.get(pathToImagesOfAvatars, fullFileName);
        image.transferTo(path);
        String ROOT_TO_IMAGES_OF_AVATARS = "/images/avatars/";
        return ROOT_TO_IMAGES_OF_AVATARS + fullFileName;
    }

    @Override
    public void deleteImageOfAvatars(String fullFileName) throws IOException {
        Path path = getFullPathToImageOfAvatars(fullFileName);
        Files.deleteIfExists(path);
    }

    @Override
    public Path getFullPathToImageOfGoods(String fullFileName) {
        Path path = Paths.get(pathToImagesOfGoods, fullFileName);
        if (Files.exists(path)) {
            return path;
        }
        throw new NotFoundException("No such path found " + path);
    }

    @Override
    public Path getFullPathToImageOfAvatars(String fullFileName) {
        Path path = Paths.get(pathToImagesOfAvatars, fullFileName);
        if (Files.exists(path)) {
            return path;
        }
        throw new NotFoundException("No such path found " + path);
    }

    @Override
    public byte[] imageToByteArray(Path path) throws IOException {
        return Files.readAllBytes(path);
    }

}
