package com.tiantian.filter;

import com.tiantian.constant.CommonConstant;
import com.tiantian.utils.security.token.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.BooleanUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 鉴权登录拦截器
 *
 * @author qi_bingo
 */
@Slf4j
@Component
public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     * 执行登录认证
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        if (this.isLoginRequest(request, response)) {
            return true;
        }
        Boolean afterFiltered = (Boolean) (request.getAttribute("jwtShiroFilter.FILTERED"));
        if (BooleanUtils.isTrue(afterFiltered)) {
            return true;
        }
        boolean allowed = false;
        try {
            allowed = this.executeLogin(request, response);
        } catch (IllegalStateException e) {
            HttpServletRequest httpRequest = WebUtils.toHttp(request);
            log.error(httpRequest.getRequestURI() + ": Not found any token");
            httpResponse.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (Exception e) {
            log.error("Validate token fail, error:{}", e.getMessage());
        }
        return allowed || super.isPermissive(mappedValue);
    }

    /**
     * 此方法重写，是为了修改认证失败状态码，以便前台重新加载
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setStatus(HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION);
        return false;
    }

    /**
     *  执行登陆
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(CommonConstant.ACCESS_TOKEN);

        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.SC_OK);
            return false;
        }
        return super.preHandle(request, response);
    }
}
