package com.brs.idm.common.interceptor;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @className InterceptorConfig
 * @description TODO
 * @author tiny lin
 * @date  2018/11/27
 * @version 1.0
*/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getIntercepotr());
    }
    @Bean
    public HandlerInterceptor getIntercepotr(){
        return new AuthInterceptor();
    }
}
