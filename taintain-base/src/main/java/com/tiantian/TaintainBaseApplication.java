package com.tiantian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaintainBaseApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(TaintainBaseApplication.class, args);
		}catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
