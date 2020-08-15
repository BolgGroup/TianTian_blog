/*
 * @Author: LYQ
 * @Date: 2019-07-27 15:10:33
 * @company: zbiti
 * @LastEditors: LYQ
 * @LastEditTime: 2019-07-30 14:41:38
 * @Description: file content
 */
package com.tiantian.configs;

import com.tiantian.interceptor.ResponseResultInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description 配置统一返回体拦截器
 */
@Configuration
public class CoreWebConfiguration implements WebMvcConfigurer {

    //统一返回体拦截器
    @Autowired
    private ResponseResultInterceptor responseResultInterceptor;

    /**
     * @description: 配置拦截器
     * @param: registry
     * @return:
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //开启统一返回体拦截器
        registry.addInterceptor(responseResultInterceptor).addPathPatterns("/**");
    }
}