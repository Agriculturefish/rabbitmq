package com.yuhao;

import com.yuhao.business.async.AsyncTaskService;
import com.yuhao.web.config.TaskExecutorConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AsyncTaskTest {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
        AsyncTaskService asyncTaskService = context.getBean(AsyncTaskService.class);
        for (int i=0;i<10;i++){
            asyncTaskService.executeAsyncTask(i);
            asyncTaskService.executeAsyncTaskPlus(i);
        }
    }
}
