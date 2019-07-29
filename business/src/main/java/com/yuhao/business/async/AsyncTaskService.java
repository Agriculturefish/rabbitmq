package com.yuhao.business.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 多线程异步并发实现
 * */
@Service
public class AsyncTaskService {

    private final Logger logger = LoggerFactory.getLogger(AsyncTaskService.class);

    /**
     * 异步方法：声明异步任务
     * 自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor
     * */
    @Async
    public void executeAsyncTask(Integer i){
        logger.info("执行异步任务"+i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer i){
        logger.info("执行异步任务+1--》"+i++);
    }

}
