package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;

import java.util.List;

public interface AdsService {

    List<Ad> getAds();

    Ad addAd(Ad Ad, MultipartFile multipartFile);

    Ad getAd(int adId);

    void geleteAd(int adId);

    CreateOrUpdateAd updateAd(int adId, Ad ad);

    List<Ads> getMyAds();

    void updateImage(int adId, MultipartFile multipartFile);







}
