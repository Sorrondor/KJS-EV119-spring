package com.app.ev119.service;

import com.app.ev119.domain.dto.*;
import com.app.ev119.domain.dto.request.MemberDTO;
import com.app.ev119.domain.dto.response.ChangePasswordDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface MyPageService {
//    회원 정보 수정
    public MemberDTO getMember(Long memberId);
    public void modifyMember(Long memberId, MemberDTO member);
    public void removeMember(Long memberId);

//    비밀번호 변경
    public void modifyPassword(Long memberId, ChangePasswordDTO password);

//    토큰을 멤버 아이디로 바꾸기
    public Long findIdByToken(Authentication tokenDTO);
}
