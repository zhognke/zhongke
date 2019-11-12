package com.example.busniess.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ClearSession {
    @Scheduled()
    public void clearSession(HttpSession session,String a){
        session.removeAttribute(a);
    }

}
