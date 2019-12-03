package com.youxia.service.user;

import com.youxia.userinfo.domain.User;
import com.youxia.userinfo.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Reference
    private UserService userService;

    public User sayHello(User user) {
        return userService.saveUser(user);
    }


}
