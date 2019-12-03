package com.youxia.authsever.service;

import com.youxia.authsever.entity.User;

public interface UserService {
    public User create(String username, String password);
}
