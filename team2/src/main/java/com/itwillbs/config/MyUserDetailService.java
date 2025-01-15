/*
 * package com.itwillbs.config;
 * 
 * import org.springframework.security.core.userdetails.User; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.itwillbs.entity.Member; import
 * com.itwillbs.repository.MemberRepository;
 * 
 * import lombok.RequiredArgsConstructor;
 * 
 * @RequiredArgsConstructor
 * 
 * @Service public class MyUserDetailService implements UserDetailsService{
 * 
 * private final MemberRepository memberRepository;
 * 
 * @Override public UserDetails loadUserByUsername(String id) throws
 * UsernameNotFoundException{
 * 
 * 
 * Member member = memberRepository.findById(id).orElseThrow(() -> new
 * UsernameNotFoundException("존재하지 않는 사용자입니다.") );
 * 
 * return User.builder() .username(member.getId()) .password(member.getPasswd())
 * .roles(member.getAuthority()) .build() ; } }
 */
package com.itwillbs.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itwillbs.repository.MemberMapper;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class MyUserDetailService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        // findById 호출
        Map<String, Object> memberData = memberMapper.findById(id);

        if (memberData == null || memberData.isEmpty()) {
            throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");
        }

        String username = (String) memberData.get("MEMBER_ID");
        String password = (String) memberData.get("PASSWD");
        String authority = (String) memberData.get("AUTHORITY");

        if (username == null) {
            throw new IllegalArgumentException("username cannot be null");
        }

        return User.builder()
                .username(username)
                .password(password)
                .roles(authority)
                .build();
    }

}
