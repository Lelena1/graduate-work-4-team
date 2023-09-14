package ru.skypro.homework.entity.images;

import lombok.Data;
import ru.skypro.homework.entity.ads.Ad;

import javax.persistence.*;


@Entity
@Data
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private Integer fileSize;
    private String mediaType;

    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;
}
