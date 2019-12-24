package com.example.busniess.shiroutil;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.Map;

public class CustomizedModularRealmAuthenticator extends ModularRealmAuthenticator {

    //存放realm
    private Map<String, Object> definedRealms;

    public void setDefinedRealms(Map<String, Object> definedRealms) {
        this.definedRealms = definedRealms;
    }


    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        super.assertRealmsConfigured();
        Realm realm = null;
//转成自己的token
        UsernamePasswordByUserTypeToken token = (UsernamePasswordByUserTypeToken) authenticationToken;
        String useType = token.getUserType();//获取用户登录类型

        if ("user".equals(useType)) {
            realm = (Realm) this.definedRealms.get("user");

        } else if ("admin".equals(useType)) {
            realm = (Realm) this.definedRealms.get("admin");
        }

        return this.doSingleRealmAuthentication(realm, authenticationToken);
    }
}
