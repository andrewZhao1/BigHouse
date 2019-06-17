package com.bighouse.service.impl;

import com.bighouse.enums.SearchUsersStatusEnum;
import com.bighouse.pojo.User;
import com.bighouse.service.FriendRequestService;
import com.bighouse.service.MyFriendsService;
import com.bighouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {

  @Autowired
  UserService userService;

  @Autowired
  MyFriendsService myFriendsService;

  @Override
  public int searchUser(String userId, String searchUserName) {

    User user = userService.getUserByUsername(searchUserName);

    if (user != null) {
      if (!user.getId().equals(userId)) {

        if (myFriendsService.getMyFriendIds(userId).contains(searchUserName)) {
          return SearchUsersStatusEnum.ALREADY_FRIENDS.status;
        } else {
          return SearchUsersStatusEnum.SUCCESS.status;
        }
      } else {
        return SearchUsersStatusEnum.NOT_YOURSELF.status;
      }
    } else {
      return SearchUsersStatusEnum.USER_NOT_EXIST.status;
    }
  }
}
