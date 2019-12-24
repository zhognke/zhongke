package com.example.busniess.shiroutil;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UsernamePasswordByUserTypeToken extends UsernamePasswordToken {
    private  String userType;//用户类型
    public UsernamePasswordByUserTypeToken(String username, String password, String userType) {
        super(username, password);
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
