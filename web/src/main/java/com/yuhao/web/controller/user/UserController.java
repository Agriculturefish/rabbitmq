
package com.yuhao.web.controller.user;

import com.yuhao.business.function.FunctionService;
import com.yuhao.web.annotation.SubToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuhao.business.rabbitmq.RabbitmqService;
import com.yuhao.business.user.UserService;
import com.yuhao.web.common.LogService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    //初始化下拉框
    private void initParameter(Model model){
         model.addAttribute("name",userService.findById("1"));
         rabbitmqService.sendMsg("用户："+userService.findById("1")+"登录！");
//    	 model.addAttribute("name","yuhao");
    }


    //加载查询页面
    @GetMapping("/index")
    @SubToken(saveToken = true)//开启一个Token
    public String index(Model model, HttpServletRequest request){
        log.info("/user  用户页面");
        model.addAttribute("subToken",request.getSession().getAttribute("subToken"));
        initParameter(model);
        return "user/user";
    }

    @RequestMapping(value = "/postForm", method = RequestMethod.POST)
    @ResponseBody
    @SubToken(removeToken = true) //开启Token验证，并且成功之后移除当前Token
    public String postForm(String userName) {
        System.out.println(System.currentTimeMillis());
        try{
            System.out.println(userName);
            Thread.sleep(1500);//暂停1.5秒后程序继续执行
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis());
        return "1";
    }

}

      
