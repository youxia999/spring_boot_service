package com.youxia.springboot.dubbo.userinfo;

import com.youxia.springboot.dubbo.domain.UserInfo;

public interface UserinfoService {
    int saveUserInfo(UserInfo userInfo);
    boolean updateUserInfo(UserInfo userInfo);
}
