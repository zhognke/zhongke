package com.example.busniess.config;


import com.example.busniess.service.MyShiroRealm;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /**
         * anon:所有url都都可以匿名访问;
         * authc: 需要认证才能进行访问;
         * user:配置记住我或认证通过可以访问；
         */
        // 拦截器
        //设置登录的页面

        shiroFilterFactoryBean.setLoginUrl("/#/login");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/loginout", "logout");
        /*
//登录接口设置
        map.put("/user/**", "anon");//user/路径下的接口都可以匿名访问
        //企业认证中心的
        map.put("/bussinessCenter/**", "authc");//企业中心所有的接口需要登录
//企业补充信息/BusinessInformation//或者拥有某个角色后
        map.put("/BusinessInformation/**", "authc");//企业中心所有的接口需要登录
        //上传文件按的接口。。。/fileUpload
        map.put("/fileUpload", "authc");//需要登
        // 融资接口需要有某个角色才能用    //Financing
        map.put("/Financing/**", "roles[vip]");//需要登录并拥有某个角色
//科技成果需要某个角色才能用
        map.put("/occupancy/**", "roles[vip]");
//工业申报需要有角色
        map.put("/industrialdeclaration/**", "roles[vip]");
//创新活动
        map.put("/innovationActivitiesApplication/**", "roles[vip]");
        map.put("/innovationActivities/**", "roles[vip]");
        map.put("/innovationActivitiesRegistration/**", "roles[vip]");
        map.put("/intention/**", "roles[vip]");
  //新闻超级管理员
        map.put("/news/**", "roles[admin]");
   //个人认证/selectAllPerson
        map.put("/person/selectAllPerson", "roles[admin]");
        map.put("/person/**", "authc");//个人认证需要登录
//专家入住
        map.put("/addProfessionals/**","roles[vip]");
//人才需求
        map.put("/talentDemand/**","roles[vip]");
        */

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }


    /**
     * shiro核心安全管理器
     *
     * @param myShiroRealm
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm);
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }
    @Bean
    public CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    /**
     * 设置加密
     *
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {

        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //使用md5加密算法进行加密
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //设置散列次数：加密次数
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }


    /**
     * 自己的relam
     */
    @Bean("myShiroRealm")
    public MyShiroRealm creatRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatche) {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatche);


        return myShiroRealm;
    }

    /**
     * 开启注解
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


}
