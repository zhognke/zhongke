package com.example.busniess.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;
import java.util.*;


@Configuration
public class DruidConfiguration {
    @Autowired
    WallFilter wallFilter;
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource=new DruidDataSource();
        // filter
        List<Filter> filters = new ArrayList<>();
        filters.add(wallFilter);
        druidDataSource.setProxyFilters(filters);
        return  druidDataSource;
    }

    /**
     * 配置 Druid 监控 之  管理后台的 Servlet
     * 内置 Servler 容器时没有web.xml 文件，所以使用 Spring Boot 的注册 Servlet 方式
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),
                "/druid/*");

        /**
         * loginUsername：Druid 后台管理界面的登录账号
         * loginPassword：Druid 后台管理界面的登录密码
         * allow：Druid 后台允许谁可以访问
         *      initParams.put("allow", "localhost")：表示只有本机可以访问
         *      initParams.put("allow", "")：为空或者为null时，表示允许所有访问
         * deny：Druid 后台拒绝谁访问
         *      initParams.put("deny", "192.168.1.20");表示禁止此ip访问
         */
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");
        initParams.put("allow", "");
        /*initParams.put("deny", "192.168.1.20");*/

        /** 设置初始化参数*/
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 配置 Druid 监控 之  web 监控的 filter
     * WebStatFilter：用于配置Web和Druid数据源之间的管理关联监控统计
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        /** exclusions：设置哪些请求进行过滤排除掉，从而不进行统计*/
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);

        /** "/*" 表示过滤所有请求*/
      bean.addUrlPatterns("/*");
//        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }



//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean() ;
//        filterRegistrationBean.setFilter(new WebStatFilter());
//        //所有请求进行监控处理
//        filterRegistrationBean.addUrlPatterns("/*");
//        //添加不需要忽略的格式信息
//        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*");
//        return filterRegistrationBean ;
//    }
@Bean(name ="wallFilter")
@DependsOn("wallConfig")
public WallFilter wallFilter(WallConfig wallConfig){
    WallFilter wallFilter = new WallFilter();
    wallFilter.setConfig(wallConfig);
    return wallFilter;
}

    @Bean(name = "wallConfig")
    public WallConfig wallConfig(){
        WallConfig wallConfig = new WallConfig();
        wallConfig.setMultiStatementAllow(true);//允许一次执行多条语句
        wallConfig.setNoneBaseStatementAllow(true);//允许一次执行多条语句
        return wallConfig;
    }



}
