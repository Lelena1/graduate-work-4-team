package ru.skypro.homework.service.ads.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.*;
import ru.skypro.homework.entity.ads.Ad;
import ru.skypro.homework.repository.ads.AdsRepository;
import ru.skypro.homework.service.ads.AdsService;

import java.io.IOException;

@Service
public class AdsServiceImpl implements AdsService {

    @Autowired
    private AdsRepository adsRepository;
    @Autowired
    private AdMapper adMapper;
    @Value("${file.download.path}")
    String imageFolderPath;

    @Override
    public AdsDto getAllAds() {
        AdsDto result = adMapper.toAdsDto(adsRepository.findAll());
        return result;
    }

    @Override
    public AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image) {
        //TODO сохраняет всё, кроме id Юзера
        Ad newAd = adMapper.toEntity(createOrUpdateAdDto);
        String imageName = image.getOriginalFilename();
        String filePath = imageFolderPath;

        newAd.setTitle(createOrUpdateAdDto.getTitle());
        newAd.setPrice(createOrUpdateAdDto.getPrice());
        newAd.setDescription(createOrUpdateAdDto.getDescription());

        newAd.setImage(filePath + imageName);
        adsRepository.save(newAd);
        return adMapper.toAdDto(newAd);
    }


    @Override
    public ExtendedAdDto getAds(Integer id) {
        //TODO сохраняет всё, кроме полей юзера
        ExtendedAdDto extendedAdDto = adMapper.toExtendedAdDto(adsRepository.findById(id).get());
        extendedAdDto.setTitle(adsRepository.findById(id).get().getTitle());
        return extendedAdDto;
    }


    @Override
    public void removeAd(Integer id) {
        //todo выдаёт 500 ошибку, если ad не найден в дб
        adsRepository.deleteById(id);
    }

    @Override
    public AdDto updateAds(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto) {
        //todo выдаёт ошибку 415 при патче
        Ad updatedAd = adMapper.toEntity(createOrUpdateAdDto);
        updatedAd.setPk(id);
        adsRepository.save(updatedAd);
        return adMapper.toAdDto(updatedAd);
    }

    @Override
    public AdsDto getAdsMe() {
        return null;
    }

    @Override
    public byte[] updateImage(Integer id, MultipartFile image) {
        return null;
    }


}
