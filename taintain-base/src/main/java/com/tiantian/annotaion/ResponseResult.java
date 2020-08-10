package com.tiantian.annotaion;


import com.tiantian.result.DefaultResult;
import com.tiantian.result.Result;

import java.lang.annotation.*;

/**
 * @author qi_bingo
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {
    Class<? extends Result>  value() default DefaultResult.class;
}