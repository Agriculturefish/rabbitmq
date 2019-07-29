
package com.yuhao.web.controller.user;

import com.yuhao.business.function.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuhao.business.rabbitmq.RabbitmqService;
import com.yuhao.business.user.UserService;
import com.yuhao.web.common.LogService;

@Controller
@RequestMapping("/user")
public class UserController {
    private String name = "dennis";
    @Autowired
    LogService log;
    @Autowired
    UserService userService;
    @Autowired
    RabbitmqService rabbitmqService;
    @Autowired
    FunctionService functionService;
    //初始化下拉框
    private void initParameter(Model model){
         model.addAttribute("name",userService.findById("1"));
         // rabbitmqService.sendMsg("用户："+userService.findById("1")+"登录！");
        functionService.init();
//    	 model.addAttribute("name","yuhao");
    }


    //加载查询页面
    @GetMapping("/index")
    public String index(Model model){
        log.info("/user  用户页面");
        initParameter(model);
        return "user/user";
    }
}

      
