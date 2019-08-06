package com.yuhao.business.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ScheduledTaskService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final Logger logger = LoggerFactory.getLogger(ScheduledTaskService.class);

    /**
     * Scheduled声明计划任务
     * fixedRate在指定时间执行
     * */
    @Scheduled(fixedRate = 50000)
    public void reportCurrentTime(){
        logger.info("每隔五秒执行一次"+dateFormat.format(new Date()));
    }
    /**
     * Scheduled声明计划任务
     * cron在指定时间执行每天14点40分，UNIX和类UNIX下系统定时任务
     * */
    @Scheduled(cron = "0 41 14 ? * *" )
    public void fixTimeExecute(){
        logger.info("在指定"+dateFormat.format(new Date())+"时间执行");
    }
}
