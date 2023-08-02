package com.zerobase.fastlms.banner.service;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;

    public List<BannerDto> list() {

        List<Banner> bannerList = bannerRepository.findAll();
        if (bannerList.isEmpty()) {
            return null;
        }
        return BannerDto.of(bannerList);
    }

    public Long countTotal() {
        return bannerRepository.countBy();
    }

    public List<BannerDto> getFrontList() {
        List<Banner> bannerList = bannerRepository.findByOpenYnIsTrueOrderBySortValue();
        return BannerDto.of(bannerList);
    }

    public BannerDto getById(long id) {
        return bannerRepository.findById(id).map(BannerDto::of).orElse(null);
    }

    public boolean add(BannerInput parameter) {
        Banner banner = Banner.builder()
                .id(parameter.getId())
                .bannerName(parameter.getBannerName())
                .imagePath(parameter.getImagePath())
                .imageName(parameter.getImageName())
                .linkUrl(parameter.getLinkUrl())
                .howToOpen(parameter.getHowToOpen())
                .sortValue(parameter.getSortValue())
                .regDt(LocalDate.now())
                .openYn(parameter.isOpenYn()).build();
        bannerRepository.save(banner);
        return true;
    }

    @Override
    public boolean checkSortValue(int sortValue) {
        Optional<Banner> optionalBanner = bannerRepository.findBySortValue(String.valueOf(sortValue));
        if (optionalBanner.isPresent()) {
            return false;
        }
        return true;

    }

    @Override
    public boolean del(String idList) {
        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }
                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }
        return true;
    }

    public boolean set(BannerInput parameter) {
        Optional<Banner> optionalBanner = bannerRepository.findById(parameter.getId());
        if (!optionalBanner.isPresent()) {
            return false;
        }

        Banner banner = optionalBanner.get();
        banner.setId(parameter.getId());
        banner.setBannerName(parameter.getBannerName());
        banner.setImagePath(parameter.getImagePath());
        banner.setImageName(parameter.getImageName());
        banner.setLinkUrl(parameter.getLinkUrl());
        banner.setHowToOpen(parameter.getHowToOpen());
        banner.setSortValue(parameter.getSortValue());
        banner.setRegDt(parameter.getRegDt());
        banner.setOpenYn(parameter.isOpenYn());
        bannerRepository.save(banner);
        return true;
    }
}
