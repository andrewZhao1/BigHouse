package com.bighouse.service.impl;

import com.bighouse.mapper.UsersMapper;
import com.bighouse.pojo.User;
import com.bighouse.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper mapper;

    @Autowired
    private Sid sid;

    @Override
    public boolean userIsExist(String username) {
        return mapper.userIsExist(username) > 0 ? true : false;
    }

    @Override
    public User getUserByUsernamePsw(String username, String password) {
        return mapper.getUserByUsernamePsw(username, password);
    }

    @Override
    public User saveUser(User user) {
        user.setQrcode("");
        user.setId(sid.nextShort());
        mapper.saveUser(user);
        return user;
    }

    @Override
    public User updateUserImage(User user) {
        mapper.updateUserImage(user);
        return getUserById(user.getId());
    }

    @Override
    public User getUserById(String id) {
        mapper.getUserById(id);
        return mapper.getUserById(id);
    }
}
