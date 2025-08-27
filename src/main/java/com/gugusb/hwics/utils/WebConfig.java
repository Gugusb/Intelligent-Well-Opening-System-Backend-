package com.gugusb.hwics.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 定义需要保护的所有路径模式
        String[] protectedPaths = {
                "/user/**",
                "/page1/**",
                "/page2/**",
                "/page3/**",
                "/page4/**",
                "/page5/**"
        };
        registry.addInterceptor(authInterceptor)
                .addPathPatterns(protectedPaths)
                .excludePathPatterns("/user/login", "/user/adduser");
    }
}
