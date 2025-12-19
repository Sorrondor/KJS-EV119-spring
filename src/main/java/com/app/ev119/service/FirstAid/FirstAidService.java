package com.app.ev119.service.FirstAid;

import com.app.ev119.domain.dto.request.firstAid.FirstAidRequestDTO;
import com.app.ev119.domain.dto.response.firstAid.FirstAidResponseDTO;
import com.app.ev119.domain.entity.FirstAid;
import com.app.ev119.repository.FirstAidRepository;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public interface FirstAidService {
    public FirstAidResponseDTO firstAidSave(FirstAidRequestDTO firstAidRequestDTO);
    public FirstAid firstAidFindById(Long id);
    public FirstAidResponseDTO getFirstAid(String message);
}
