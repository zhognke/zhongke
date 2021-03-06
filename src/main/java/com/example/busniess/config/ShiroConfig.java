package com.example.busniess.config;


import com.example.busniess.filter.ShiroSessionFilter;
import com.example.busniess.service.AdministratorRealm;
import com.example.busniess.service.MyShiroRealm;
import com.example.busniess.shiroutil.CustomizedModularRealmAuthenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;


@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /*设置自定义拦截器，当访问某些自定义url时，使用这个filter进行验证*/
//        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();

        //map里面key值为myFilter,表示所有名为myFilter的过滤条件使用这个自定义的filter
//        filters.put("myFilter", new MyFilter());
//        shiroFilterFactoryBean.setFilters(filters);


        /**
         * anon:所有url都都可以匿名访问;
         * authc: 需要认证才能进行访问;
         * user:配置记住我或认证通过可以访问；
         */
        // 拦截器
        //设置登录的页面

        Map<String, String> map = new LinkedHashMap<>();
//     map.put("/**", "authc");
//        map.put("/loginout", "logout");

//登录接口设置
        map.put("/user/**", "anon");//user/路径下的接口都可以匿名访问
        //企业认证中心的
        map.put("/bussinessCenter/showByPage", "anon");//企业中心所有的接口需要登录
        map.put("/bussinessCenter/findBussinessCenter", "anon");
        map.put("/bussinessCenter/**", "authc");//企业中心所有的接口需要登录
//企业补充信息/BusinessInformation//或者拥有某个角色后
        map.put("/BusinessInformation/**", "authc");//企业中心所有的接口需要登录
        //上传文件按的接口。。。/fileUpload
//        map.put("/fileUpload", "authc");//需要登

        /////////////////////////////
//科技成果需要某个角色才能用/occupancy
        map.put("/occupancy/showByPage", "anon");
        map.put("/occupancy/selectOneOccupancyById", "anon");
        map.put("/occupancy/getHotIndustry", "anon");
        map.put("/occupancy/selectPieImg", "anon");
//        map.put("/occupancy/**", "authc");
//工业申报需要有角色1.新增/addDeclaration删除状态2./deleteById批量删除3./deleteByBatch删除4./realDeleteById
//      更新  /updateDeclaration修改/updateDeclaration /showByPage   roles[vip]
        map.put("/industrialdeclaration/selectOneOccupancyById", "anon");//具体的
        map.put("/industrialdeclaration/showByPage", "anon");//显示检索
        map.put("/industrialdeclaration/getCompanyList", "anon");//获取公司名字
        map.put("/industrialdeclaration/showByPageForCenter", "authc");//个人展示
//        map.put("/industrialdeclaration/**", "authc");
//创新活动申请表/innovationActivitiesApplication
//        map.put("/innovationActivitiesApplication/showByPage", "anon");
//        map.put("/innovationActivitiesApplication/getById", "anon");
        map.put("/innovationActivitiesApplication/**", "authc");

//创新活动表/innovationActivities
        map.put("/innovationActivities/showByPage", "anon");
        map.put("/innovationActivities/showById", "anon");
        map.put("/innovationActivities/**", "authc");
//创新活动报名表
//        map.put("/innovationActivitiesRegistration/showByPage", "anon");
//        map.put("/innovationActivitiesRegistration/getById", "anon");
        map.put("/innovationActivitiesRegistration/**", "authc");
//项目融资/Financing  /Financing  /findFinancingByCondition


        map.put("/Financing/findFinancingByCondition", "anon");
        map.put("/Financing/selectOneById", "anon");
        map.put("/Financing/selectIndustry", "anon");
        map.put("/Financing/getIndustryProp", "anon");
        map.put("/Financing/**", "authc");

        //意向表intention
        map.put("/intention/addIntention", "anon");
        map.put("/intention/**", "authc");
        //新闻超级管理员
        map.put("/news/**", "anon");
//通知
        map.put("/notification/**", "authc");
        //个人认证/person
        //  map.put("/person/selectAllPerson", "roles[admin]");
        //  map.put("/person/**", "authc");//个人认证需要登录
//专家入住/professionals
        map.put("/professionals/showByPage", "anon");
        map.put("/professionals/showById", "anon");
        map.put("/professionals/showHot", "anon");
        map.put("/professionals/**", "authc");
//人才需求/talentDemand
        map.put("/talentDemand/showByPage", "anon");
        map.put("/talentDemand/showById", "anon");
        map.put("/talentDemand/demandsIndustryProp", "anon");
        map.put("/talentDemand/**", "authc");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        shiroFilterFactoryBean.setLoginUrl("/needLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/permissiondenied");
        return shiroFilterFactoryBean;
    }


    /**
     * shiro核心安全管理器
     *
     * @param myShiroRealm
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm,
                                           @Qualifier("administratorRealm") AdministratorRealm administratorRealm,
                                           @Qualifier("authenticator") CustomizedModularRealmAuthenticator authenticator) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(authenticator);

        List a = new ArrayList();
        a.add(myShiroRealm);
        a.add(administratorRealm);
        securityManager.setRealms(a);
//        securityManager.setRealm(myShiroRealm);
        securityManager.setSessionManager(sessionManager());
//        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    @Bean
    public CacheManager memoryConstrainedCacheManager() {
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

    //设置session
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 设置session过期时间3600000
        List a = new ArrayList();
        a.add(new ShiroSessionFilter());
        sessionManager.setSessionListeners(a);
        sessionManager.setGlobalSessionTimeout(3600000);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(sessionIdCookie());
        return sessionManager;
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

    @Bean("administratorRealm")
    public AdministratorRealm creatAdminRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatche) {
        AdministratorRealm administratorRealm = new AdministratorRealm();
        administratorRealm.setCredentialsMatcher(hashedCredentialsMatche);


        return administratorRealm;
    }

    /**
     * 自己的认证器
     */
    @Bean("authenticator")
    public CustomizedModularRealmAuthenticator creatAuthenticator(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm,
                                                                  @Qualifier("administratorRealm") AdministratorRealm administratorRealm) {

        CustomizedModularRealmAuthenticator authenticator = new CustomizedModularRealmAuthenticator();
        Map map = new HashMap();
        map.put("user", myShiroRealm);
        map.put("admin", administratorRealm);


        authenticator.setDefinedRealms(map);
        return authenticator;
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

    //设置cook的名字
    @Bean(name = "sessionIdCookie")
    public SimpleCookie sessionIdCookie() {
        SimpleCookie cookie = new SimpleCookie();
        cookie.setName("ZHONGKEHUAIBIE");
        cookie.setHttpOnly(true);
//        cookie.setMaxAge(18000);
        return cookie;
    }


}
