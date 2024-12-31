package com.itwillbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwillbs.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	Member findByIdAndPasswd(Integer id, String Passwd);
}