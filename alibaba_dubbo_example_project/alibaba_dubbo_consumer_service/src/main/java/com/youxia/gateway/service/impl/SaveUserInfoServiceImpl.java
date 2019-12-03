package com.youxia.gateway.service.impl;


import com.alibaba.dubbo.config.annotation.Reference;
import com.youxia.gateway.service.SaveUserInfoService;
import com.youxia.springboot.dubbo.domain.UserInfo;

import com.youxia.springboot.dubbo.userinfo.UserinfoService;
import org.springframework.stereotype.Service;

@Service
public class SaveUserInfoServiceImpl implements SaveUserInfoService {

    @Reference
    private UserinfoService userinfoService;

    public int saveUserInfo(){
        UserInfo userInfo=new UserInfo();
        userInfo.setUserAge(60);
        userInfo.setUserJob("president");
        userInfo.setUserName("tumple");
        userInfo.setUserPhone("000010001");
        userInfo.setUserSalary(1000000.00);
        userInfo.setUserAddress("washtondc");
        return userinfoService.saveUserInfo(userInfo);
    }

}
