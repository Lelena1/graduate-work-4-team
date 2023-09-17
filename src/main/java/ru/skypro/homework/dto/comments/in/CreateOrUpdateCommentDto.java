package ru.skypro.homework.dto.comments.in;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
/**
 * DTO for {@link ru.skypro.homework.dto.comments.in.CreateOrUpdateCommentDto}
 */
@Data
public class CreateOrUpdateCommentDto {
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 8, max = 64, message = "Ограничение на количество символов: от 8 до 64")
    private String text;
}
