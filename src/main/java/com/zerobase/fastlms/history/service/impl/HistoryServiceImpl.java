package com.zerobase.fastlms.history.service.impl;

import com.zerobase.fastlms.history.dto.HistoryDto;
import com.zerobase.fastlms.history.entity.History;
import com.zerobase.fastlms.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HistoryServiceImpl implements com.zerobase.fastlms.history.service.HistoryService {

    private final HistoryRepository historyRepository;

    @Override
    public boolean saveLoginHistory(History history) {
        historyRepository.save(history);
        return true;
    }

    @Override
    public List<HistoryDto> detail(String userId) {

        List<History> historyList = historyRepository.findByLoginId(userId);

        return HistoryDto.of(historyList);
    }

}
