package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class CreateOrUpdateComment {
    @Min(4)
    @Max(84)
    private String text;
}
