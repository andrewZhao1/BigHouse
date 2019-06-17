package com.bighouse.enums;

public enum SearchUsersStatusEnum {
  SUCCESS(0, "OK"),
  USER_NOT_EXIST(1, "该用户不存在..."),
  NOT_YOURSELF(2, "不能添加你自己..."),
  ALREADY_FRIENDS(3, "该用户已经是你的好友");

  public final Integer status;
  public final String msg;

  SearchUsersStatusEnum(Integer status, String msg) {
    this.status = status;
    this.msg = msg;
  }
}
