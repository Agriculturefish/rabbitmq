
package com.yuhao;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.yuhao")
@EnableConfigurationProperties
public class Application {
	public static void main(String[] args) throws Exception {
//		ConfigurableBeanFactory factory = applicationContext.getBeanFactory();
		SpringApplication.run(Application.class, args);
	}

}

      
