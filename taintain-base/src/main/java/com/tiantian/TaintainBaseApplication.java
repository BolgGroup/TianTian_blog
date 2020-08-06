package com.tiantian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class TaintainBaseApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(TaintainBaseApplication.class, args);
			System.setProperty("spring.devtools.restart.enabled", "false");
		}catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
