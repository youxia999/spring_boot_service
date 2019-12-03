package com.youxia.userinfo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.youxia.userinfo.domain.User;
import com.youxia.userinfo.service.UserService;

@Service(filter = {"tracing"})
public class UserServiceImpl implements UserService {
    @Override
    public User saveUser(User user) {
        user.setUserId(1);
        user.setUserName(user.getUserName());
        System.out.println(user.toString());
        return  user;
    }
}
