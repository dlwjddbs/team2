package com.itwillbs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //자동으로 객체 생성 하겠다
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final MyUserDetailService myUserDetailsService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests().requestMatchers(null).hashCode().and().formLogin();
		return http
				.authorizeHttpRequests(authorizeHttpRequestsCutomizer 
						-> authorizeHttpRequestsCutomizer
						.requestMatchers("/", "/login/**", "/dist/**", "/build/**", "/docs/**", "/examples/**", "/forms/**", "/images/**", "/layout/**", "/plugins/**").permitAll()
						.requestMatchers("/mypage/**",  "/appoint/**").authenticated()
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.anyRequest()
						.authenticated()
						)
			    .csrf(csrf -> csrf
			            .ignoringRequestMatchers(
			            		  "/appoint/**"
			            		, "/salary/**"
			            		, "/attendance/**"
			            		, "/system/**"
			            		, "/approval/**"
			            		, "/ajax/**"
			            		, "/mypage/**"
			            		, "/department/**"
			            		, "/order/**"
			            		, "/manufacture/**"
			            		, "/rest/**"
			            		, "/equipment/**"
			            		, "/clientinfo/**"
			            		, "/iteminfo/**"
			            		, "/warehouse/**"
			            		, "/dashboard/**"
			            ) // 특정 경로 CSRF 비활성화
			        )				
				.formLogin(formLoginCustomizer
						-> formLoginCustomizer
						.loginPage("/login")
						.loginProcessingUrl("/login")
						.usernameParameter("id")
						.passwordParameter("passwd")
		                .successHandler(authenticationSuccessHandler()) // 로그인 성공 핸들러
		                .failureHandler(authenticationFailureHandler()) // 로그인 실패 핸들러
						)
				.logout(logoutCustomizer
						-> logoutCustomizer
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/login")
						.deleteCookies("menuCategory")  // 권한이 다른 유저 로그인 시 카테고리 출력 문제처리 위함.
						)
				.userDetailsService(myUserDetailsService)
				.build();
		
	}
	
	  @Bean
	    public AuthenticationSuccessHandler authenticationSuccessHandler() {
	        return (request, response, authentication) -> {
	        	String username = authentication.getName();
	        	String remember = request.getParameter("remember");
	        	
	        	Cookie cookie = new Cookie("rememberedUsername", username);
	        	cookie.setPath("/");
	            if ("on".equals(remember)) {
	                cookie.setMaxAge(60 * 60 * 24 * 30);
	                cookie.setHttpOnly(true);
	                cookie.setSecure(true);
	            } else {
	                cookie.setMaxAge(0);
	            }
	            response.addCookie(cookie);
	        	
	            System.out.println("로그인 성공!");
	            System.out.println("사용자 이름: " + username);
	            System.out.println("권한: " + authentication.getAuthorities());
	            response.sendRedirect("/"); // 로그인 성공 후 이동할 경로
	        };
	    }

	    @Bean
	    public AuthenticationFailureHandler authenticationFailureHandler() {
	        return (request, response, exception) -> {
	            System.out.println("로그인 실패: " + exception.getMessage());
	            response.sendRedirect("/login?error=true"); // 로그인 실패 후 이동할 경로
	        };
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder(); // 비밀번호 암호화를 위한 인코더
	    }
	    
//	    @Bean
//	    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
//	        return new GrantedAuthorityDefaults(""); // 접두사 제거
//	    }

}
