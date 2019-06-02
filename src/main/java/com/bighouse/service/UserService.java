package com.bighouse.service;

import com.bighouse.pojo.User;

public interface UserService {
    boolean userIsExist(String username);
    User getUserByUsernamePsw(String username, String password);
    User saveUser(User user);
}
