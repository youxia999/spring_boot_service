package com.youxia.authsever.mapper;

import com.youxia.authsever.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUsername(String username);
    User save(User user);
}
