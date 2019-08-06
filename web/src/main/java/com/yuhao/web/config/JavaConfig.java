package com.yuhao.web.config;

import com.yuhao.business.function.FunctionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring 4.x java配置 全局替代xml
 * 使用原则：全局使用java配置【如数据库MVC相关配置】，业务Bean配置使用注解配置
 * 【@Service、@Component...】
 * */
@Configuration
public class JavaConfig {
    @Bean
    public FunctionService functionService(){
        return new FunctionService();
    }


}
