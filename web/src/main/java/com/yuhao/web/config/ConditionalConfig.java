package com.yuhao.web.config;

import com.yuhao.business.conditional.LinuxCondition;
import com.yuhao.business.conditional.WindowsCondition;
import com.yuhao.business.conditional.imple.LinuxListService;
import com.yuhao.business.conditional.imple.WindowsListService;
import com.yuhao.business.conditional.inter.ListService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalConfig {

    @Bean
    @Conditional(WindowsCondition.class)
    /**
     * 通过 @Conditional注解符合Windows条件实例化WindowsListService
     * */
    public ListService windowsListService(){
        return new WindowsListService();
    }

    @Bean
    @Conditional(LinuxCondition.class)
    /**
     * 通过 @Conditional注解符合Linux条件实例化LinuxListService
     * */
    public ListService linuxListService(){
        return new LinuxListService();
    }
}
