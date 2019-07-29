
package com.yuhao.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yuhao.web.common.LogService;

@Controller
public class LoginController {
	 @Autowired
	 LogService log;
	 
	 //加载查询页面
    @GetMapping("/index")
    public String index(Model model){
        log.info("/index  首页页面");
        return "index";
    }
}

      
