/*
 * @Author: bing
 * @Date: 2020-07-02 10:54:14
 * @Description: file content
 */
package com.tiantian.configs;

import com.tiantian.entity.TbUser;
import com.tiantian.utils.util.Guid;
import com.tiantian.utils.util.JwtUtil;
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

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Autowired
    RedisUtil redisUtil;

    @Bean
    public Docket createRestApi(){
        TbUser tbUser = new TbUser();
        tbUser.setUserId("admin");
        String secret = Guid.newGuid();
        redisUtil.set("JWT_admin", secret);
        String jwt = JwtUtil.sign(tbUser, secret, 3600);
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("authToken").description("authToken").modelRef(new ModelRef("string")).parameterType("query")
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