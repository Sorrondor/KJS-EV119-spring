package com.app.ev119.service.FirstAid;

import com.app.ev119.repository.FirstAidKeywordsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FirstAidKeywordServiceImpl implements FirstAidKeywordService {

    private final FirstAidKeywordsRepository firstAidKeywordsRepository;

    @Override
    public boolean existFirstAidKeyword(String keyword) {
        return firstAidKeywordsRepository.existsByKeyword(keyword);
    }
}
