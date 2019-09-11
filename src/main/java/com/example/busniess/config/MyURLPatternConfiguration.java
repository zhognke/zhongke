package com.example.busniess.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyURLPatternConfiguration implements WebMvcConfigurer {
    /**
     * 手动指定外部文件的位置映射
     * @param registry WebMvcConfigurationSupport
     */
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/fileData/**").addResourceLocations("file:D:/myFile/");
//        super.addResourceHandlers(registry);
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/img/**").addResourceLocations("file:F:/myFile/");

//    }





}
