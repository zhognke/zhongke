package com.example.busniess.config;

import com.example.busniess.filter.MyHander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyURLPatternConfiguration implements WebMvcConfigurer {
    @Value("${upload.resourceLocation}")
    String resourceLocation;
    @Autowired
    MyHander myHander;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations(resourceLocation);

        //registry.addResourceHandler("/img/**").addResourceLocations("file:D://appData//");
        //registry.addResourceHandler("/img/**").addResourceLocations("file:/project/tools/tomcat8.5/introduce/img/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myHander).addPathPatterns("/**");

    }
}
