package com.youxia.gateway.controller;

import com.youxia.gateway.service.SaveUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private SaveUserInfoService saveUserInfoService;

    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    @ResponseBody
    public String welcome(){
        int result=saveUserInfoService.saveUserInfo();
        if (result>0){
            return "success";
        }else {
            return "failed";
        }
    }
}
