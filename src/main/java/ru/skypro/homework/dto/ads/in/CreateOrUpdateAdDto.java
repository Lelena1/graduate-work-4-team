package ru.skypro.homework.dto.ads.in;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
/**
 * DTO for {@link ru.skypro.homework.dto.ads.in.CreateOrUpdateAdDto}
 */
@Data
public class CreateOrUpdateAdDto {
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 4, max = 32, message = "Размер заголовка от 4 до 32 символов")
    private String title;
    @NotEmpty(message = "Поле не может быть пустым")
    @Min(value = 0, message = "Цена не может быть отрицательной")
    @Max(value = 10000000, message = "Цена не может быть больше 10 млн.")
    private Integer price;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 8, max = 64, message = "Ограничение текста описания: от 8 до 64 символов")
    private String description;
}
