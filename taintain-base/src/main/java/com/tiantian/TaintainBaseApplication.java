package com.tiantian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan(basePackages = { "com.tiantian" })
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //默认不加载数据源配置
@MapperScan("com.tiantian.mapper")    //扫包注解
@EnableCaching  //缓存注解
@EnableScheduling //定时注解
@EnableTransactionManagement    //事务注解
@EnableSwagger2 //swagger注解
public class TaintainBaseApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(TaintainBaseApplication.class, args);
            System.setProperty("spring.devtools.restart.enabled", "false");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
