package com.example.busniess.filter;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component

public class MyHander implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {



        Subject subject= SecurityUtils.getSubject();

        response.setHeader("Access-Control-Expose-Headers", "sessionStatus");
        response.setCharacterEncoding("UTF-8");

        if (subject.isAuthenticated()) {
            response.addHeader("sessionStatus", "ok");

        } else if (subject.getPrincipal() == null) {


//请求头设置参数
            response.addHeader("sessionStatus", "timeout");



        }



        return true;
    }
}
