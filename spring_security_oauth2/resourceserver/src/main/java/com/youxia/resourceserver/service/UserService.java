package com.youxia.resourceserver.service;

import com.youxia.resourceserver.entity.User;

public interface UserService {
    public User create(String username, String password);
}
