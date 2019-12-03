package com.youxia.controller;

import com.youxia.userinfo.domain.User;
import com.youxia.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * 测试方法，浏览器访问 /demo/index 可以看到响应结果了
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        User user=new User();
        user.setUserName("userinfo");
        return userServiceImpl.sayHello(user).getUserName();
    }


}
