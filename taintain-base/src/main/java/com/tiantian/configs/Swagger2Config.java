/*
 * @Author: bing
 * @Date: 2020-07-02 10:54:14
 * @Description: file content
 */
package com.tiantian.configs;

import com.tiantian.entity.SysUser;
import com.tiantian.utils.util.Guid;
import com.tiantian.utils.security.JwtUtil;
import com.tiantian.utils.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.tiantian.constant.CommonConstant.SWAGGER_TOKEN;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Autowired
    RedisUtil redisUtil;

    @Bean
    public Docket createRestApi(){
        SysUser sysUser = new SysUser();
        sysUser.setUserId("admin");
        String secret = Guid.newGuid();
        redisUtil.set(SWAGGER_TOKEN, secret);
        String jwt = JwtUtil.sign(sysUser, secret, 3600);
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Access-Token").description("Access-Token").modelRef(new ModelRef("string")).parameterType("header")
                .required(true).defaultValue(jwt).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tiantian"))
                .paths(PathSelectors.any()).build().globalOperationParameters(pars);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("天天博客")
                .description("This is TianTian_Blog!")
                .version("1.0")
                .build();
    }

}