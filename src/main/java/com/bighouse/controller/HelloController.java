package com.bighouse.controller;

import com.bighouse.pojo.MyFriends;
import com.bighouse.service.MyFriendsService;
import com.bighouse.utils.FastDFSClient;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class HelloController {

  private Logger logger = Logger.getLogger(HelloController.class);

  @Autowired
  private MyFriendsService service;

//    @Autowired
//    private FastDFSClient fastDFSClient;

  @Autowired
  private FastFileStorageClient storageClient;

  @Autowired
  private ThumbImageConfig thumbImageConfig;

  @RequestMapping("/hello")
  public String hello() {
    return "hello bie house";
  }


  @RequestMapping("/selectAllFriends")
  public List<MyFriends> selectAll() {
    return service.selectAll();
  }

  @PostMapping("/upload") //new annotation since 4.3
  public String singleFileUpload() {

    File file = new File("/Users/zhaojunhua/work/workspace/localIMG/WechatIMG1.jpg");
    // 上传并且生成缩略图
    StorePath storePath = null;
    try {
      storePath = this.storageClient.uploadImageAndCrtThumbImage(
          new FileInputStream(file), file.length(), "jpg", null);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    // 带分组的路径
    logger.info(storePath.getFullPath());
    // 不带分组的路径
    logger.info(storePath.getPath());
    // 获取缩略图路径
    String path = thumbImageConfig.getThumbImagePath(storePath.getPath());
    logger.info(path);
    return path;
  }


}
