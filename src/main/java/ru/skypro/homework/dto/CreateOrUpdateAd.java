package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class CreateOrUpdateAd {
    @Min(4)
    @Max(32)
    private String title;
    @Min(0)
    @Max(10000000)
    private Integer price;
    @Min(8)
    @Max(64)
    private String description;

}
