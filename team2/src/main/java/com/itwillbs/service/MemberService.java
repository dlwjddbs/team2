package com.itwillbs.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.entity.Member;
import com.itwillbs.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;
    
    public Optional<Member> findById(Integer id) {
    	return memberRepository.findById(id);
    }
    
    public Member findByIdAndPasswd(MemberDTO memberDTO) {
    	return memberRepository.findByIdAndPasswd(memberDTO.getId(), memberDTO.getPasswd());
    }

    @Transactional
    public void updateMember(MemberDTO memberDTO) {
        if (memberDTO.getId() == null) {
            throw new IllegalArgumentException("ID가 없습니다.");
        }

        Member member = memberRepository.findById(memberDTO.getId())
        	    .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다. ID: " + memberDTO.getId()));

        Optional.ofNullable(memberDTO.getName()).ifPresent(member::setName);
        Optional.ofNullable(memberDTO.getResidentRegistNum()).ifPresent(member::setResidentRegistNum);
        Optional.ofNullable(memberDTO.getAddressNum()).ifPresent(member::setAddressNum);
        Optional.ofNullable(memberDTO.getAddress1()).ifPresent(member::setAddress1);
        Optional.ofNullable(memberDTO.getAddress2()).ifPresent(member::setAddress2);
        Optional.ofNullable(memberDTO.getEmail()).ifPresent(member::setEmail);
        Optional.ofNullable(memberDTO.getProfilePic()).ifPresent(member::setProfilePic);
        Optional.ofNullable(memberDTO.getPasswd()).ifPresent(member::setPasswd);
        Optional.ofNullable(memberDTO.getUseYn()).ifPresent(member::setUseYn);
        Optional.ofNullable(memberDTO.getCertificate()).ifPresent(member::setCertificate);
        Optional.ofNullable(memberDTO.getEducation()).ifPresent(member::setEducation);

        memberRepository.save(member);
    }    

    @Transactional
    public void updateEducation(Integer memberId, String certificate, String education) {
    	Member member = memberRepository.findById(memberId)
    			.orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다. ID: " + memberId));
    	
    	member.setCertificate(certificate);
    	member.setEducation(education);
    	
    	memberRepository.save(member);	
    	
    }

}

