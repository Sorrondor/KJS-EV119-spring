package com.app.ev119.repository;

import com.app.ev119.domain.dto.response.firstAid.FirstAidResponseDTO;
import com.app.ev119.domain.entity.*;
import com.app.ev119.domain.type.UrgencyType;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
@Slf4j @Commit
@Transactional
public class FirstAidRepositoryTest {

    @Autowired
    private FirstAidRepository firstAidRepository;

    @Autowired
    private FirstAidProceduresRepository firstAidProceduresRepository;

    @Autowired
    private FirstAidKeywordsRepository firstAidKeywordsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void insertFirstAid(){
        FirstAid firstAd = new FirstAid();

        firstAd.setUrgency(UrgencyType.CRITICAL);

        firstAidRepository.save(firstAd);
    }

    @Test
    public void insertProcedures(){

        String testResultText = "1. 이거는 테스트 글이고/2. 구분 문자는 슬래시일거야/3. 다섯 항목 정도로 할거고/4. 난 지금 매우 피곤해/5. 잠을 잔게 잔게 아닌 것 같긴 해";
        String[] firstAidProducresArray = testResultText.split("/");

        for(String str : firstAidProducresArray){
            FirstAidProcedures firstAidProcedures = new FirstAidProcedures();
            firstAidProcedures.setProceduresContent(str);
            firstAidProcedures.setFirstAid(firstAidRepository.findById(1L).orElseThrow(()-> new RuntimeException("FirstAid Not Found")));

            firstAidProceduresRepository.save(firstAidProcedures);
        }

    }

    @Test
    public void insertKeyword(){
        String keyword = "테스트";
        FirstAidKeywords firstAidKeywords = new FirstAidKeywords();
        firstAidKeywords.setKeyword(keyword);
        firstAidKeywords.setFirstAid(firstAidRepository.findById(1L).orElseThrow(()-> new RuntimeException("FirstAid Not Found")));

        firstAidKeywordsRepository.save(firstAidKeywords);
    }

    @Test
    public void findFirstAid(){

        String message = "테스트";
        if( firstAidKeywordsRepository.existsByKeyword(message) ) {
            JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
            QFirstAidKeywords qFirstAidKeywords = QFirstAidKeywords.firstAidKeywords;
            JPAQuery<FirstAidKeywords> query = jpaQueryFactory.selectFrom(qFirstAidKeywords).where(qFirstAidKeywords.keyword.like("%" + message + "%"));

            List<FirstAidKeywords> resultList = query.fetch();
            resultList.stream().map(FirstAidKeywords::toString).forEach(log::info);

            FirstAidKeywords firstAidKeywords = resultList.get(0);
            log.info("찾은 키워드 객체: {}", firstAidKeywords);
            FirstAid firstAid = firstAidRepository.findById(firstAidKeywords.getFirstAid().getId()).get();
            log.info("찾은 응급처치 객체: {}", firstAid);
            List<FirstAidProcedures> firstAidProceduresList = firstAid.getFirstAidProcedures();
            log.info("찾은 응급처치 과정");
            firstAidProceduresList.stream().map(FirstAidProcedures::toString).forEach(log::info);

            FirstAidResponseDTO firstAidResponseDTO = new FirstAidResponseDTO(firstAid);
            firstAidResponseDTO.setFirstAidProcedures(firstAidProceduresList);
            firstAidResponseDTO.setFirstAidKeywords(resultList);
        } else {
            log.info("{} 이라는 키워드가 없습니다.", message);
        }

    }
}
