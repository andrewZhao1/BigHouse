package com.bighouse.mapper;

import com.bighouse.pojo.MyFriends;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyFriendsMapper {

    List<MyFriends> selectAll();
}