
package com.yuhao;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import javax.naming.Context;

@SpringBootApplication
@ComponentScan(value = "com.yuhao")
@EnableConfigurationProperties
@EnableCaching
/**
 * @EnableConfigurationProperties根据类路径中的jar包依赖为当前项目进行自动配置
 * 关闭特定的自动配置@SpringBootApplication（exclude={DataSourceAutoConfiguration.class}）
 * */
public class Application {
	public static void main(String[] args) throws Exception {
//		ConfigurableBeanFactory factory = applicationContext.getBeanFactory();
		SpringApplication.run(Application.class, args);
		//关闭banner
		// SpringApplication app = new SpringApplication(Application.class);
		// app.setBannerMode(Banner.Mode.OFF);
		// app.run(args);
	}


}

      
