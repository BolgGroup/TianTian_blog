package com.tiantian.configs;

import com.tiantian.utils.realm.JWTRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Slf4j
public class JWTShiroConfig {

    /**
     * 用于JWT token认证的realm
     */
    @Bean("jwtRealm")
    public Realm jwtShiroRealm() {
        JWTRealm jwtRealm = new JWTRealm();
        return jwtRealm;
    }

    /**
     * 注册shiro的Filter，拦截请求
     */
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean(SecurityManager securityManager) throws Exception {
        FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean<Filter>();
        filterRegistration.setFilter((Filter) shiroFilter(securityManager).getObject());
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setAsyncSupported(true);
        filterRegistration.setEnabled(true);
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC);

        return filterRegistration;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置自定义拦截器
        Map<String, Filter> filterMap = shiroFilterFactoryBean.getFilters();
        shiroFilterFactoryBean.setFilters(filterMap);

        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 设置无权限时跳转的 url;
        // shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");
        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/imgcode", "noSessionCreation,anon");// 验证码
        filterChainDefinitionMap.put("/", "noSessionCreation,anon");// 开放登陆接口
        filterChainDefinitionMap.put("/login", "noSessionCreation,anon");// 开放登陆接口
        // 配置记住我或认证通过可以访问的地址
        // 开发时使用记住我功能时开放下面这行代码
        // filterChainDefinitionMap.put("/**", "user");

        // swagger 相关代码
        filterChainDefinitionMap.put("/swagger-ui.html", "noSessionCreation,anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "noSessionCreation,anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "noSessionCreation,anon");
        filterChainDefinitionMap.put("/v2/**", "noSessionCreation,anon");
        filterChainDefinitionMap.put("/csrf", "noSessionCreation,anon");
        // druid监控台 需要使用session
        filterChainDefinitionMap.put("/druid/**", "anon");
        // actuator接口，后期可能需要加入JWT权限控制
        filterChainDefinitionMap.put("/actuator/**", "noSessionCreation,anon");
        filterChainDefinitionMap.put("/**", "noSessionCreation,authcToken");

        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        log.info("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

}
