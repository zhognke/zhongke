package com.example.busniess.service;

import com.example.busniess.dao.ManagerDao;
import com.example.busniess.entity.Manager;
import com.example.busniess.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.Set;

public class AdministratorRealm extends AuthorizingRealm {
    @Autowired
    ManagerDao managerDao;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        Manager manager = (Manager) principalCollection.getPrimaryPrincipal();//获取对象
        if(manager==null){
            throw  new UnknownAccountException();
        }
        Set<String> set=new LinkedHashSet<>();
//        Set<String> set=UserServiceImpl.findMyRole(user.getUserName());//查询当前用户的角色
        if(manager.getManage().contains("admin")){
//            set=UserServiceImpl.findAllRole();//vip赋予全部角色

        }

        AuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo(set);//验证权限


        return authorizationInfo;





    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String manage = upToken.getUsername();//获取用户名


        Manager manager=   managerDao.selectManagerByManage(manage);

        if(manager==null) {
            throw new UnknownAccountException("用户不存在");
        }


        String password = manager.getPassword();//数据库密码
        ByteSource credentialsSalt = ByteSource.Util.bytes(manager.getManage());//盐用户名唯一

        SimpleAuthenticationInfo info = null;
        info = new SimpleAuthenticationInfo(manager, password, credentialsSalt, getName());

        return info;

    }
}
