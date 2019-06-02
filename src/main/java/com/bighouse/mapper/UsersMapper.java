package com.bighouse.mapper;

import com.bighouse.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {
    int userIsExist(String username);

    User getUserByUsernamePsw(String username, String password);

    void saveUser(User user);
}