package com.tiantian.configs;

import com.tiantian.interceptor.LogInterceptor;
import com.tiantian.interceptor.OverAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:拦截器配置
 * @author: qibing
 * @date: 2020年6月18日14:31:07
 **/

@Configuration
public class BaseLoginConfig implements WebMvcConfigurer {

    @Autowired
    private OverAuthInterceptor overAuthInterceptor;

    @Autowired
    private LogInterceptor logInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 开启日志拦截器
        registry.addInterceptor(logInterceptor).excludePathPatterns("/", "/login", "/assets/**", "/static/**",
                "/base/watermark/**", "/error/**");

        // 开启越权拦截器
        InterceptorRegistration addInterceptor = registry.addInterceptor(overAuthInterceptor);
        addInterceptor.excludePathPatterns("/error/**");
        addInterceptor.excludePathPatterns("/assets/**");
        addInterceptor.excludePathPatterns("/static/**");// 排除静态资源
        addInterceptor.excludePathPatterns("/login");
        addInterceptor.excludePathPatterns("/");
        addInterceptor.excludePathPatterns("/logout");
        addInterceptor.addPathPatterns("/**");

    }
}
