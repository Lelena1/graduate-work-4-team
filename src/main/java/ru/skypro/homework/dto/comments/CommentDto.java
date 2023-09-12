package ru.skypro.homework.dto.comments;

import lombok.Data;

/**
 * DTO for {@link ru.skypro.homework.dto.comments.CommentDto}
 */
@Data
public class CommentDto {
    private Integer pk;
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private String text;
    private Integer createdAt;

}
