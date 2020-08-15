package com.tiantian.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 日志拦截器
 * @author: qibing
 * @date: 2020/06/18
 */
@Component
public class LogInterceptor implements HandlerInterceptor {

    //日志
    private final static Logger loger = LoggerFactory.getLogger(OverAuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }
}