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

        if (subject.isAuthenticated()) {
            response.addHeader("session-status", "ok");
            return true;
        } else if (subject.getPrincipal() == null) {

            response.setCharacterEncoding("UTF-8");

            response.addHeader("session-status", "timeout");

        }

        return true;
    }
}
