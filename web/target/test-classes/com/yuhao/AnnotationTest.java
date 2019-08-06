package com.yuhao;

import com.yuhao.business.annotation.WislyService;
import com.yuhao.web.config.WislyConfig;
import com.yuhao.web.service.DemoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 因为部署的module的pom中引了其他module的依赖以后，当前项目中其他的module还没有打包，所以找不到DemoService
 * .NoSuchBeanDefinitionException: No qualifying bean of type 'com.yuhao.business.annotation.WislyService' available
 * 同一个module下组合扫描注解可以扫描指定目录下的文件；不同module下组合扫描注解不可以扫描指定目录下的文件
 * */
public class AnnotationTest {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WislyConfig.class);
        WislyService wislyService = context.getBean(WislyService.class);
        wislyService.outputResult();

        // DemoService demoService = context.getBean(DemoService.class);
        // demoService.inputout();
        context.close();
    }
}
