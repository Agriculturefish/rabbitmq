package com.yuhao.business.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * java配置测试服务类
 * */
public class FunctionService {
    private final Logger logger = LoggerFactory.getLogger(FunctionService.class);

    public void init(){
        logger.info("FunctionService已创建");
    }
}
