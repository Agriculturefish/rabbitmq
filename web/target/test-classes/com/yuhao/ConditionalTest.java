package com.yuhao;

import com.yuhao.business.conditional.inter.ListService;
import com.yuhao.web.config.ConditionalConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConditionalTest {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionalConfig.class);
        ListService listService = context.getBean(ListService.class);
        System.out.println(context.getEnvironment().getProperty("os.name")+"系统下的列表命令为"+listService.showListCmd());
    }
}
