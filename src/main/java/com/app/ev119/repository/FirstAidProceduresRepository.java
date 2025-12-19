package com.app.ev119.repository;


import com.app.ev119.domain.entity.FirstAidProcedures;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FirstAidProceduresRepository extends JpaRepository<FirstAidProcedures, Long> {
    public FirstAidProcedures save(FirstAidProcedures procedure);
    public Optional<FirstAidProcedures> findById(Long id);
}
