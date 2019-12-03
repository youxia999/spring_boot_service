package com.youxia.authsever.service;

import com.youxia.authsever.entity.User;
import com.youxia.authsever.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserMapper userMapper;

    @Override
    public User create(String username, String password) {
        User user = new User();
        user.setUsername(username);
        password = "{bcrypt}"+passwordEncoder.encode(password);
        user.setPassword(password);
        User u = userMapper.save(user);
        return u;
    }
}
