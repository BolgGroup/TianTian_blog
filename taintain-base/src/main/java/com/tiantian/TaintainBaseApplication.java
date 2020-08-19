package com.tiantian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author qi_bingo
 */
@ComponentScan(basePackages = { "com.tiantian" })
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.tiantian.mapper")
@EnableCaching
@EnableTransactionManagement
@ServletComponentScan(basePackages = { "com.tiantian.base" })
@EnableSwagger2
public class TaintainBaseApplication {

    public static void main(String[] args) {
            SpringApplication.run(TaintainBaseApplication.class, args);
    }

}
