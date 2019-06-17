package com.bighouse.controller;

import com.bighouse.service.FriendRequestService;
import com.bighouse.utils.BigHouseJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fRequest")
public class FriendRequestController {

  @Autowired
  FriendRequestService service;

  @PostMapping("/search")
  public BigHouseJSONResult searchUser(String userId, String searchUserName) {

    if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(searchUserName)) {
      return BigHouseJSONResult.errorMsg("");
    }

    return BigHouseJSONResult.ok(service.searchUser(userId, searchUserName));
  }
}
