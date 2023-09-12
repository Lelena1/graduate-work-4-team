package ru.skypro.homework.dto.comments;

import lombok.Data;

import java.util.List;
/**
 * DTO for {@link ru.skypro.homework.dto.comments.CommentsDto}
 */
@Data
public class CommentsDto {
    private Integer count;
    private List<CommentDto> results;
}
