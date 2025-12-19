package com.app.ev119.repository;

import com.app.ev119.domain.entity.FirstAidKeywords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FirstAidKeywordsRepository extends JpaRepository<FirstAidKeywords, Long> {
    public FirstAidKeywords save(FirstAidKeywords keywords);
    public Optional<FirstAidKeywords> findById(Long id);
    public Optional<FirstAidKeywords> findByKeyword(String keyword);
    public boolean existsByKeyword(String keyword);
}
