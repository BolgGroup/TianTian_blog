package com.tiantian.utils.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 项目启动时执行
 * 这里通过设定@Order(value)的值来指定执行顺序
 * @author qi_bingo
 */
@Component
@Order(value = 1)
public class RsaApplicationRunner implements ApplicationRunner {

	/** 密钥文件位置 */
	@Value("${security.keypath}")
	private String keyPath;
	
	private static final Log log = LogFactory.getLog(RsaUtils.class);
    
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("开始生成RSA密钥对");
		try {
			RsaUtils.getKeyPair(keyPath);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("生成RSA密钥失败");
		}
		log.info("生成RSA密钥成功");
	}
}
