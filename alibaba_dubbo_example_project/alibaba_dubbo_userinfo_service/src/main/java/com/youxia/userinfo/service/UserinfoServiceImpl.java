package com.youxia.userinfo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.youxia.springboot.dubbo.domain.UserInfo;
import com.youxia.springboot.dubbo.userinfo.UserinfoService;
import com.youxia.userinfo.mapper.UserBaseInfoMapper;
import com.youxia.userinfo.model.UserBaseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserinfoServiceImpl implements UserinfoService {

    private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;

    @Override
    public int saveUserInfo(UserInfo userInfo) {
        logger.info("save user info start");
        UserBaseInfo userBaseInfo=new UserBaseInfo();
        userBaseInfo.setUserAddress(userInfo.getUserAddress());
        userBaseInfo.setUserAge(userInfo.getUserAge());
        userBaseInfo.setUserJob(userInfo.getUserJob());
        userBaseInfo.setUserName(userInfo.getUserName());
        userBaseInfo.setUserPhone(userInfo.getUserPhone());
        userBaseInfo.setUserSalary(userInfo.getUserSalary());
        return userBaseInfoMapper.insertUserInfo(userBaseInfo);
    }

    @Override
    public boolean updateUserInfo(UserInfo userInfo) {
        return false;
    }
}
