package com.zerobase.fastlms.history.service;

import com.zerobase.fastlms.history.dto.HistoryDto;
import com.zerobase.fastlms.history.entity.History;

import java.util.List;

public interface HistoryService {
    boolean saveLoginHistory(History history);

    List<HistoryDto> detail(String userId);
}
