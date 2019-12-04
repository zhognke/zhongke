package com.example.busniess.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.subject.Subject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class ShiroSessionFilter implements SessionListener {
    @Override
    public void onStart(Session session) {
        System.out.println(System.currentTimeMillis());
        System.out.println("会话开始"+session);
    }

    @Override
    public void onStop(Session session) {
        System.out.println("会话停止"+session);

    }

    @Override
    public void onExpiration(Session session) {


        System.out.println(System.currentTimeMillis());
        System.out.println("会话过期"+session);
    }
}
