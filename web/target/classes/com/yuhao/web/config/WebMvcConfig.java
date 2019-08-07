package com.yuhao.web.config;

import com.yuhao.web.servlet.WebListener;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * 保持对SpringMVC的全面支持和扩展，而且还要维持SpringBoot不写xml配置的优势
 * SpringBoot2.0版本使用WebMvcConfigurationSupport 来扩展SpringMvc的功能
 * 特殊注解 @EnableWebMvc全面接管SpringMvc的配置，SpringBoot所有关于SpringMvc的自动配置都会失效（包括静态资源页的访问等等）需要我们自己手段配置所有的类
 * */
@Configuration
public class WebMvcConfig  extends WebMvcConfigurationSupport{
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        // super.addViewControllers(registry);
        // 浏览器发送 /ice 请求，同样展示index页面，但是不读取数据
        registry.addViewController("/ice").setViewName("index");
    }

    @Bean
    public WebListener webListener(){
        return new WebListener();
    }

    @Bean
    public ServletListenerRegistrationBean<WebListener> servletListenerRegistrationBean(){
        return new ServletListenerRegistrationBean<WebListener>(new WebListener());
    }

}
