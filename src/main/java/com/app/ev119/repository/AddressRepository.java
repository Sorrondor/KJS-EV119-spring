package com.app.ev119.repository;

import com.app.ev119.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
//    단일 주소 추가
//    단일 주소 업데이트
    default void saveAddress(Address address){
        save(address);
    }

//    목록 형태의 주소 추가
//    목록 형태의 주소 업데이트
    default void saveAddresses(List<Address> addresses){
        saveAll(addresses);
    }

//    멤버의 모든 주소 조회
    public List<Address> findByMember_Id(Long memberId);

//    모든 주소 목록 조회
    default List<Address> findAllAddresses(){
        return findAll();
    }

//    멤버의 모든 주소 삭제
    public void deleteByMember_Id(Long memberId);
}
