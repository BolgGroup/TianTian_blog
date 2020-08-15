package com.tiantian.annotaion;


import com.tiantian.result.DefaultResult;
import com.tiantian.result.Result;

import java.lang.annotation.*;

/** @description 接口返回结果增强  会通过拦截器拦截后放入标记，在ResponseResultHandler 进行结果处理
 * @author qi_bingo
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {
    Class<? extends Result>  value() default DefaultResult.class;
}