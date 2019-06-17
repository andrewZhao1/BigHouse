package com.bighouse.service;

import com.bighouse.pojo.User;
import com.bighouse.pojo.bo.UserBO;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;

public interface UserService {

  User getUserByUsername(String username);

  User getUserByUsernamePsw(String username, String password);

  User saveUser(User user) throws Exception;

  User updateUserImage(User user);

  User getUserById(String id);

  User updateUserNickName(User user);

  StorePath uploadFaceImg(UserBO userBO) throws Exception;
}
