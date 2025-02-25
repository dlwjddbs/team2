package com.itwillbs.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    // ID로 회원 조회
    Map<String, Object> findById(String id);

    // ID와 비밀번호로 회원 조회
    Map<String, Object> findByIdAndPasswd(@Param("id") String id, @Param("passwd") String passwd);

    // 회원 정보 업데이트
    void updateMember(Map<String, Object> memberData);

    // 자격증 및 교육 정보 업데이트
    void updateEducation(Map<String, Object> educationData);

    // 비밀번호 변경
    void updatePassword(@Param("id") String id, @Param("newPassword") String newPassword);
    
    List<Map<String, Object>> getAllBankNames();
}