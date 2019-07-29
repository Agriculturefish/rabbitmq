package com.yuhao;

import com.yuhao.business.BusApplication;
import com.yuhao.business.async.AsyncTaskService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BusApplication.class})// 指定启动类
public class AppTest {
    private final Logger logger = LoggerFactory.getLogger(AppTest.class);

    @Autowired
    AsyncTaskService asyncTaskService;

    @Test
    public void testAsyncTask(){
        for (int i=0;i<10;i++){
            asyncTaskService.executeAsyncTask(i);
            asyncTaskService.executeAsyncTaskPlus(i);
        }

    }

}
