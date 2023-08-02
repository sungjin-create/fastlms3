package com.zerobase.fastlms.history.repository;

import com.zerobase.fastlms.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByLoginId(String loginId);
}
