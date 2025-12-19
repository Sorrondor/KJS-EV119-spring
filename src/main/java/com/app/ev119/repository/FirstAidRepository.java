package com.app.ev119.repository;

import com.app.ev119.domain.entity.FirstAid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FirstAidRepository extends JpaRepository<FirstAid, Long> {
    public Optional<FirstAid> findById(Long id);
    public FirstAid save(FirstAid firstAid);
}
