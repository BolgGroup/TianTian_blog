package com.tiantian.handler;

import com.tiantian.annotaion.ResponseResult;
import com.tiantian.interceptor.ResponseResultInterceptor;
import com.tiantian.result.DefaultErrorResult;
import com.tiantian.result.DefaultResult;
import com.tiantian.result.Result;
import com.tiantian.utils.request.RequestContextHolderUtil;
import com.tiantian.utils.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 接口响应体处理器
 * @author qi_bingo
 */
@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        // 判断请求，是否有包装标记
        ResponseResult responseResultAnn = (ResponseResult) request
                .getAttribute(ResponseResultInterceptor.RESPONSE_RESULT_ANN);
        return responseResultAnn != null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {

        log.info(request.getURI() + "处理返回体");

        ResponseResult responseResultAnn = (ResponseResult) RequestContextHolderUtil.getRequest()
                .getAttribute(ResponseResultInterceptor.RESPONSE_RESULT_ANN);

        Class<? extends Result> resultClazz = responseResultAnn.value();

        if (resultClazz.isAssignableFrom(DefaultResult.class)) {
            if (body instanceof DefaultErrorResult) {
                DefaultErrorResult defaultErrorResult = (DefaultErrorResult) body;
                return DefaultResult.builder().code(Integer.valueOf(defaultErrorResult.getCode()))
                        .message(defaultErrorResult.getMessage()).data(defaultErrorResult.getErrors()).build();
            } else if (body instanceof String) {
                return JsonUtil.object2Json(DefaultResult.success(body));
            }

            return DefaultResult.success(body);
        }
        return body;
    }

}
