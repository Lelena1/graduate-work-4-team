package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;

import java.util.List;

public interface AdsService {

    List<AdDto> getAds();

    AdDto addAd(AdDto AdDto, MultipartFile multipartFile);

    AdDto getAd(int adId);

    void geleteAd(int adId);

    CreateOrUpdateAdDto updateAd(int adId, AdDto adDto);

    List<AdsDto> getMyAds();

    void updateImage(int adId, MultipartFile multipartFile);







}
