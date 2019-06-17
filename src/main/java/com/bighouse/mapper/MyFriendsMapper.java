package com.bighouse.mapper;

import com.bighouse.pojo.MyFriends;

import java.util.List;

//@Mapper
public interface MyFriendsMapper {

  List<MyFriends> selectAll();

  List<String> getMyFriendIds(String userId);

}