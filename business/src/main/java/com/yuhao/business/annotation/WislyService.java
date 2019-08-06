package com.yuhao.business.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WislyService {
    private final Logger logger = LoggerFactory.getLogger(WislyService.class);

    public void outputResult(){
        logger.info("从组合注解获取bean");
    }
}

