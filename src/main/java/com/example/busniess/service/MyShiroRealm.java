package com.example.busniess.service;

import com.example.busniess.entity.User;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    UserService UserServiceImpl;

    /**
     * 授权的方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)throws ShiroException {
        User user = (User) principalCollection.getPrimaryPrincipal();//获取对象
        if(user==null){
            throw  new UnknownAccountException();
        }

        Set<String> set=UserServiceImpl.findMyRole(user.getUserName());//查询当前用户的角色
        if(user.getUserName().contains("admin")){
            set=UserServiceImpl.findAllRole();//vip赋予全部角色

        }

        AuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo(set);//验证权限


        return authorizationInfo;
    }

    /**
     * 认证的方法
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws ShiroException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String userName = upToken.getUsername();//获取用户名
        User user = UserServiceImpl.findUserByName(userName);//根据名字获取用户对象
        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        }

        String password = user.getPassword();//数据库密码
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUserName());//盐用户名唯一

        SimpleAuthenticationInfo info = null;
        info = new SimpleAuthenticationInfo(user, password, credentialsSalt, getName());

        return info;
    }
}
