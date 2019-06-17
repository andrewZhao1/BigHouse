package com.bighouse.service.impl;

import com.bighouse.mapper.UsersMapper;
import com.bighouse.pojo.User;
import com.bighouse.pojo.bo.UserBO;
import com.bighouse.service.UserService;
import com.bighouse.utils.FileUtils;
import com.bighouse.utils.QRCodeUtils;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UsersMapper mapper;

  @Autowired
  private FastFileStorageClient storageClient;

  @Autowired
  private Sid sid;

  @Autowired
  private QRCodeUtils qrCodeUtils;

  @Override
  public User getUserByUsername(String username) {
    return mapper.getUserByUsername(username);
  }

  @Override
  public User getUserByUsernamePsw(String username, String password) {
    return mapper.getUserByUsernamePsw(username, password);
  }

  @Override
  public User saveUser(User user) throws Exception {

    String userId = sid.nextShort();
    String qrCodePath = "C:/Users/qa261/Desktop/localIMG/" + userId + "qrcode.png";
    qrCodeUtils.createQRCode(qrCodePath, "BigHouse_qrCode:" + user.getUsername());
    File file = new File(qrCodePath);
    // 上传并且生成缩略图
    StorePath storePath = this.storageClient.uploadFile(
        new FileInputStream(file), file.length(), "png", null);
    user.setQrcode(storePath.getPath());
    user.setId(userId);
    mapper.saveUser(user);
    return user;
  }

  @Override
  public StorePath uploadFaceImg(UserBO userBO) throws Exception {

    String base64Data = userBO.getFaceData();
    String userFacePath =
        "C:/Users/qa261/Desktop/localIMG/" + userBO.getUserId() + "userface64.png";

    FileUtils.base64ToFile(userFacePath, base64Data);
    File file = new File(userFacePath);
    // 上传并且生成缩略图
    return this.storageClient.uploadImageAndCrtThumbImage(
        new FileInputStream(file), file.length(), "png", null);
  }

  @Override
  public User updateUserImage(User user) {
    mapper.updateUserImage(user);
    return getUserById(user.getId());
  }

  @Override
  public User getUserById(String id) {
    return mapper.getUserById(id);
  }

  @Override
  public User updateUserNickName(User user) {
    mapper.updateUserNickName(user);
    return getUserById(user.getId());
  }
}
