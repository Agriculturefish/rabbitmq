
package com.yuhao.web.controller;

import com.wisely.service.HelloService;
import com.yuhao.business.annotation.WislyService;
import com.yuhao.business.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yuhao.web.common.LogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    LogService log;
    @Autowired
    HelloService helloService;
    //加载查询页面
    @GetMapping("/index")
    public String index(Model model, HttpServletRequest request){
        // HttpSession session = request.getSession();
        // session.setAttribute("subToken","12345678");
        log.info("/index  首页页面"+helloService.sayHello());
        return "index";
    }


}

      
