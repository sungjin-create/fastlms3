package com.zerobase.fastlms.banner.dto;

import com.zerobase.fastlms.banner.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BannerDto {
    Long id;

    String bannerName;
    String imagePath;
    String imageName;

    String linkUrl;
    String howToOpen;

    String sortValue;

    LocalDate regDt;
    boolean openYn;

    long totalCount;

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .bannerName(banner.getBannerName())
                .imagePath(banner.getImagePath())
                .imageName(banner.getImageName())
                .linkUrl(banner.getLinkUrl())
                .howToOpen(banner.getHowToOpen())
                .sortValue(banner.getSortValue())
                .regDt(banner.getRegDt())
                .openYn(banner.isOpenYn())
                .build();
    }

    public static List<BannerDto> of(List<Banner> bannerList) {
        if (bannerList.isEmpty()) {
            return null;
        }
        List<BannerDto> list = new ArrayList<>();
        for (Banner x : bannerList) {
            list.add(BannerDto.of(x));
        }
        return list;
    }

}
