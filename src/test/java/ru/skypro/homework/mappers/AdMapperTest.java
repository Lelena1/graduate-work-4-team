package ru.skypro.homework.mappers;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ads.in.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.out.AdDto;
import ru.skypro.homework.entity.ads.Ad;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AdMapperTest {
    private final AdMapper adMapper = Mappers.getMapper(AdMapper.class);

    @Test
    public void shouldMapToAd() {

        //given
        CreateOrUpdateAdDto createOrUpdateAdDto=new CreateOrUpdateAdDto();
        createOrUpdateAdDto.setTitle("title");
        createOrUpdateAdDto.setPrice(25000);
        createOrUpdateAdDto.setDescription("description");

        //when
        Ad ad=adMapper.toAd(createOrUpdateAdDto);
        System.out.println(ad);

        //then
        assertThat(ad).isNotNull();
        assertThat(ad.getTitle()).isEqualTo(createOrUpdateAdDto.getTitle());
        assertThat(ad.getPrice().intValue()).isEqualTo(createOrUpdateAdDto.getPrice());
        assertThat(ad.getDescription()).isEqualTo(createOrUpdateAdDto.getDescription());
        assertThat(ad.getPk()).isNull();
        assertThat(ad.getImage()).isNull();
        assertThat(ad.getAuthor()).isNull();

    }

    @Test
    void shouldMapToDto() {

        //given
        Ad ad=new Ad();
        ad.setPk(25);
        ad.setImage("path");
        ad.setTitle("title");
        ad.setPrice(5000);
        ad.setDescription("description");
//        ad.setAuthor((2,"Ivan","Ivanov","phone","email","image","username","password"));

        //when
        AdDto adDto=adMapper.toAdDto(ad);
        System.out.println(adDto);

        //then
        assertThat(adDto).isNotNull();
        assertThat(adDto.getPk().intValue()).isEqualTo(ad.getPk());
        assertThat(adDto.getImage()).isEqualTo(ad.getImage());
        assertThat(adDto.getTitle()).isEqualTo(ad.getTitle());
        assertThat(adDto.getPrice().intValue()).isEqualTo(ad.getPrice());
//        assertThat(adDto.getAuthor().intValue()).isEqualTo(ad.getAuthor());

    }

    @Test
    void shouldMapToExtendedAdDto() {
    }

    @Test
    void toAdsDto() {
    }

    @Test
    void toInteger() {
    }

    @Test
    void updateAds() {
    }
}