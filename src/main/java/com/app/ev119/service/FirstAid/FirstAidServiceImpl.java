package com.app.ev119.service.FirstAid;

import com.app.ev119.domain.dto.response.firstAid.FirstAidResponseDTO;
import com.app.ev119.domain.entity.FirstAid;
import com.app.ev119.repository.FirstAidRepository;
import com.app.ev119.repository.FirstAidProceduresRepository;
import org.springframework.stereotype.Service;

@Service
public class FirstAidServiceImpl implements FirstAidService {

    private FirstAidRepository firstAidRepository;
    private FirstAidProceduresRepository firstAidProceduresRepository;

    @Override
    public Long firstAidSave(FirstAid firstAid) {
        firstAidRepository.save(firstAid);
        return firstAid.getId();
    }

    @Override
    public FirstAid firstAidFindById(Long id) {
        return null;
    }

    @Override
    public FirstAidResponseDTO getFirstAid(String message) {


        return null;
    }
}
