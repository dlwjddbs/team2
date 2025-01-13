package com.itwillbs.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itwillbs.entity.Member;
import com.itwillbs.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyUserDetailService implements UserDetailsService{
	
	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException{
		
		
		Member member = memberRepository.findById(id).orElseThrow(()
				-> new UsernameNotFoundException("존재하지 않는 사용자입니다.")
				);
		
		return User.builder()
				.username(member.getId())
				.password(member.getPasswd())
				.roles(member.getAuthority())
				.build()
				;
	}
}
