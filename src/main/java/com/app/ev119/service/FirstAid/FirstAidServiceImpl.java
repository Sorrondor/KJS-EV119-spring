package com.app.ev119.service.FirstAid;

import com.app.ev119.domain.dto.request.firstAid.FirstAidRequestDTO;
import com.app.ev119.domain.dto.response.firstAid.FirstAidResponseDTO;
import com.app.ev119.domain.entity.FirstAid;
import com.app.ev119.domain.entity.FirstAidKeywords;
import com.app.ev119.domain.entity.FirstAidProcedures;
import com.app.ev119.domain.entity.QFirstAidKeywords;
import com.app.ev119.exception.FirstAidException;
import com.app.ev119.repository.FirstAidKeywordsRepository;
import com.app.ev119.repository.FirstAidRepository;
import com.app.ev119.repository.FirstAidProceduresRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FirstAidServiceImpl implements FirstAidService {

    private final FirstAidRepository firstAidRepository;
    private final FirstAidProceduresRepository firstAidProceduresRepository;
    private final EntityManager entityManager;
    private final FirstAidKeywordService firstAidKeywordService;
    private final FirstAidKeywordsRepository firstAidKeywordsRepository;

    @Override
    public FirstAidResponseDTO firstAidSave(FirstAidRequestDTO firstAidRequestDTO) {
        FirstAid firstAid = new FirstAid();
        firstAid.setUrgency(firstAidRequestDTO.getUrgency());
        Long id = firstAidRepository.save(firstAid).getId();
        for (String procedures : firstAidRequestDTO.getFirstAidProcedures()) {
            FirstAidProcedures firstAidProcedures = new FirstAidProcedures();
            firstAidProcedures.setFirstAid(firstAid);
            firstAidProcedures.setProceduresContent(procedures);
            firstAidProceduresRepository.save(firstAidProcedures);
        }
        for(String keyword : firstAidRequestDTO.getFirstAidKeywords()){
            FirstAidKeywords firstAidKeywords = new FirstAidKeywords();
            firstAidKeywords.setFirstAid(firstAid);
            firstAidKeywords.setKeyword(keyword);
            firstAidKeywordsRepository.save(firstAidKeywords);
        }
        FirstAidResponseDTO firstAidResponseDTO = new FirstAidResponseDTO(firstAidRequestDTO);
        return firstAidResponseDTO;
    }

    @Override
    public FirstAid firstAidFindById(Long id) {
        return null;
    }

    @Override
    public FirstAidResponseDTO getFirstAid(String message) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QFirstAidKeywords qFirstAidKeywords = QFirstAidKeywords.firstAidKeywords;
        JPAQuery<FirstAidKeywords> query = jpaQueryFactory.selectFrom(qFirstAidKeywords).where(qFirstAidKeywords.keyword.contains(message));

        List<FirstAidKeywords> resultList = query.fetch();
        FirstAidKeywords firstAidKeywords = resultList.get(0);
        FirstAid firstAid = firstAidRepository.findById(firstAidKeywords.getFirstAid().getId()).orElseThrow(() -> new FirstAidException("FirstAid Not Found"));
        List<FirstAidProcedures> firstAidProceduresList = firstAid.getFirstAidProcedures();

        FirstAidResponseDTO firstAidResponseDTO = new FirstAidResponseDTO(firstAid);
        firstAidResponseDTO.setFirstAidProcedures(firstAidProceduresList.stream().map(FirstAidProcedures::getProceduresContent).toList());
        firstAidResponseDTO.setFirstAidKeywords(resultList.stream().map(FirstAidKeywords::getKeyword).toList());

        return firstAidResponseDTO;
    }
}
