package ru.skypro.homework.dto.ads;

import lombok.Data;

import java.util.List;
/**
 * DTO for {@link ru.skypro.homework.dto.ads.AdsDto}
 */
@Data
public class AdsDto {
    private Integer count;
    private List<AdDto> results;
}
