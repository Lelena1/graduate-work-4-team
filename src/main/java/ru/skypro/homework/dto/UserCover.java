package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UserCover {
    private Integer id;
    private String filePath;
    private String fileSize;
    private String mediaType;
}
