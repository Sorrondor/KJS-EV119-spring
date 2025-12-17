package com.app.ev119.repository;

import com.app.ev119.domain.entity.FirstAid;
import com.app.ev119.domain.entity.FirstAidKeywords;
import com.app.ev119.domain.entity.FirstAidProcedures;
import com.app.ev119.domain.type.UrgencyType;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

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
}
