package com.korit.projectrrs.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebFileConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 프론트엔드
        // const serverUrl = "http://localhost:8080/c드라이브 이후 경로

        registry.addResourceHandler("/uploads/**") // URL 경로
                .addResourceLocations("file:C:/uploads/"); // 실제 파일 경로
        // http://localhost:8080/uploads/가 C:/uploads/에 매핑
    }
}
