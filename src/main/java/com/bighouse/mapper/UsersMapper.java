package com.bighouse.mapper;

import com.bighouse.pojo.User;

//@Mapper
public interface UsersMapper {

  User getUserByUsername(String username);

  User getUserByUsernamePsw(String username, String password);

  void saveUser(User user);

  void updateUserImage(User user);

  User getUserById(String id);

  void updateUserNickName(User user);
}