package ru.skypro.homework.dto.comments.out;

import lombok.Data;

/**
 * DTO for {@link ru.skypro.homework.dto.comments.out.CommentDto}
 */
@Data
public class CommentDto {
    private Integer pk;
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private String text;
    private Long createdAt;
}
