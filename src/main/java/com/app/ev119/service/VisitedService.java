package com.app.ev119.service;

import com.app.ev119.domain.dto.VisitedDTO;
import com.app.ev119.domain.entity.Member;
import com.app.ev119.domain.entity.Visited;

import java.util.List;

public interface VisitedService {
//    병원 방문 이력
    public List<VisitedDTO> findVisitedLogs (Long memberId);
    public void addVisitedLog(Long memberId, VisitedDTO visitedDTO);

    public void removeVisitedLog(Long memberId, VisitedDTO visitedDTO);
    public void removeAllVisitedLog(Long memberId);
}
