package com.youxia.resourceserver.mapper;

import com.youxia.resourceserver.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUsername(String username);
    User save(User user);
}
