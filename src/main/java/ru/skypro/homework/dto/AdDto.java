package ru.skypro.homework.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AdDto {
    int author;
    String image;
    int pk;
    int price;
    String title;
}
