package com.bighouse.service.impl;

import com.bighouse.mapper.MyFriendsMapper;
import com.bighouse.pojo.MyFriends;
import com.bighouse.service.MyFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyFriendsServiceImpl implements MyFriendsService {

    @Autowired
    private MyFriendsMapper mapper;

    @Override
    public List<MyFriends> selectAll() {
        return mapper.selectAll();
    }
}
