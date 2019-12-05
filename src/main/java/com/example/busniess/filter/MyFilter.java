package com.example.busniess.filter;

import org.apache.http.HttpResponse;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class MyFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {

        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Expose-Headers", "sessionStatus");
        response.setCharacterEncoding("UTF-8");

        if (subject.isAuthenticated()) {
            response.addHeader("sessionStatus", "ok");
            return true;
        } else if (subject.getPrincipal() == null) {


//请求头设置参数
            response.addHeader("sessionStatus", "timeout");



        }

        return true;
    }
}
