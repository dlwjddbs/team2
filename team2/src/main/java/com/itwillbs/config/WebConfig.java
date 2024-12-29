package com.itwillbs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//	프로필 이미지 등록을 위해 작성
	
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /images/** URL 요청을 ./uploads 디렉토리로 매핑
        registry.addResourceHandler("/images/**")
        		.addResourceLocations("file:./uploads/", "classpath:/static/images/");
    }
}
