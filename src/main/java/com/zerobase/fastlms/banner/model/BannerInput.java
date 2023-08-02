package com.zerobase.fastlms.banner.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BannerInput {

    Long id;
    String bannerName;

    String imagePath;
    String imageName;
    String linkUrl;

    String howToOpen;
    String sortValue;
    LocalDate regDt;
    boolean openYn;

}

