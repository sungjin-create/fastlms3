package com.zerobase.fastlms.history.dto;

import com.zerobase.fastlms.history.entity.History;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HistoryDto {

    String loginId;
    LocalDateTime loginDt;
    String ip;
    String userAgent;
    long seq;


    public static HistoryDto of(History history) {
        return HistoryDto.builder()
                .ip(history.getIp())
                .loginId(history.getLoginId())
                .loginDt(history.getLoginDt())
                .userAgent(history.getUserAgent())
                .build();
    }

    public static List<HistoryDto> of(List<History> histories) {

        if (histories == null) {
            return null;
        }

        List<HistoryDto> historyDtoList = new ArrayList<>();
        for (History x : histories) {
            historyDtoList.add(HistoryDto.of(x));
        }

        return historyDtoList;

    }
}


