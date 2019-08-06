package com.yuhao.business.login;

import com.yuhao.business.annotation.WislyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    public void outputResult(){
        logger.info("用户登录");
    }
}
