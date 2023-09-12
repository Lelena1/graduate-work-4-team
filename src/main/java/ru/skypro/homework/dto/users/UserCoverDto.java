package ru.skypro.homework.dto.users;

import lombok.Data;

@Data
public class UserCoverDto {
    private Integer id;
    private String filePath;
    private String fileSize;
    private String mediaType;
}
