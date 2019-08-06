package com.yuhao.web.config;

import com.yuhao.web.annotation.WislyConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("com.yuhao.business.scheduled")
// @WislyConfiguration("com.yuhao.business.scheduled")
//******自定义组合注解不能跨模块访问扫描目录下的文件****
@EnableScheduling
/**
 * 开启对计划任务的支持
 * */
public class TaskSchedulerConfig {

}
