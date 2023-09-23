package ru.skypro.homework.service.ads.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.out.AdDto;
import ru.skypro.homework.dto.ads.out.AdsDto;
import ru.skypro.homework.dto.ads.in.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.out.ExtendedAdDto;
import ru.skypro.homework.entity.ads.Ad;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.exceptions.NotFoundException;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.repository.ads.AdsRepository;
import ru.skypro.homework.service.ads.AdsService;
import ru.skypro.homework.service.image.ImageService;
import ru.skypro.homework.service.users.UserService;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class AdsServiceImpl implements AdsService {

    private final AdMapper adMapper;

    private final AdsRepository adsRepository;

    private final ImageService imageService;
    private final UserService userService;

    public AdsServiceImpl(AdMapper adMapper,
                          AdsRepository adsRepository,
                          ImageService imageService,
                          UserService userService
    ) {
        this.adMapper = adMapper;
        this.adsRepository = adsRepository;
        this.imageService = imageService;
        this.userService = userService;
    }

    @Override
    public AdsDto getAllAds() {
        List<Ad> adList = adsRepository.findAll();
        if (adList.isEmpty()) {
            return null;
        } else {
            return adMapper.toAdsDto(adList);
        }
    }

    @Override
    @Transactional
    public AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image) {
        User author = userService.getAuthor();
        try {
            String urlToImage = imageService.consumeImageOfGoods(image);
            Ad ad = adMapper.toAd(createOrUpdateAdDto);
            ad.setAuthor(author);
            ad.setImage(urlToImage);
            adsRepository.save(ad);
            return adMapper.toAdDto(ad);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AdsDto getAdsMe() {
        User author = userService.getAuthor();
        List<Ad> adsList = adsRepository.findAllByAuthor(author);
        return adMapper.toAdsDto(adsList);
    }

    @Override
    public ExtendedAdDto getAds(Integer id) {
        Optional<Ad> adOptional = adsRepository.findByPkIs(id);
        if (adOptional.isEmpty()) {
            throw new NotFoundException("Ad with such id not found: " + id);
        } else {
            Ad ad = adOptional.get();
            return adMapper.toExtendedAdDto(ad);
        }
    }

    @Override
    @Transactional
    public AdDto updateAds(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto) {
        Optional<Ad> adOptional = adsRepository.findByPkIs(id);
        if (adOptional.isEmpty()) {
            throw new NotFoundException("Ad with such id not found: " + id);
        } else {
            Ad adTarget = adOptional.get();
            adMapper.updateAds(createOrUpdateAdDto, adTarget);
            adsRepository.save(adTarget);
            return adMapper.toAdDto(adTarget);
        }
    }

    @Override
    @Transactional
    public void removeAd(Integer id) {
        Optional<Ad> adOptional = adsRepository.findByPkIs(id);
        if (adOptional.isEmpty()) {
            throw new NotFoundException("Ad with such id not found: " + id);
        } else {
            Ad ad = adOptional.get();
            adsRepository.delete(ad);
        }
    }

    @Override
    @Transactional
    public byte[] updateImage(Integer id, MultipartFile image) throws IOException {
        Optional<Ad> adOptional = adsRepository.findByPkIs(id);
        if (adOptional.isEmpty()) {
            throw new NotFoundException("Ad with such id not found: " + id);
        } else {
            Ad ad = adOptional.get();
            String currentUrlToImage = ad.getImage();

            if (currentUrlToImage != null) {
                String[] split = currentUrlToImage.split("/");
                String fullFileName = split[split.length - 1];
                imageService.deleteImageOfGoods(fullFileName);
            }

            String newUrlToImage = imageService.consumeImageOfGoods(image);

            ad.setImage(newUrlToImage);
            adsRepository.save(ad);

            String[] split = newUrlToImage.split("/");
            String fullFileName = split[split.length - 1];

            Path path = imageService.getFullPathToImageOfGoods(fullFileName);
            return imageService.imageToByteArray(path);
        }

    }

    @Override
    public byte[] getImage(String imageId) throws IOException {
        if (imageId == null || imageId.isEmpty()) {
            throw new NotFoundException("Ad do not have photo");
        }
        try {
            Path fullPathToImageOfGoods = imageService.getFullPathToImageOfGoods(imageId);
            return imageService.imageToByteArray(fullPathToImageOfGoods);
        } catch (NoSuchFileException exception) {
            throw new NotFoundException("File with photo was not found at this root" + imageId);
        } catch (IOException exception) {
            throw new IOException("Error during reading occurred ", exception);
        }
    }

}
