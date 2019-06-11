package com.bighouse.controller;

import com.bighouse.pojo.User;
import com.bighouse.pojo.bo.UserBO;
import com.bighouse.pojo.vo.UserVO;
import com.bighouse.service.UserService;
import com.bighouse.utils.BigHouseJSONResult;
import com.bighouse.utils.FileUtils;
import com.bighouse.utils.MD5Utils;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("u")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private ThumbImageConfig thumbImageConfig;

//    @Autowired
//    private FastDFSClient fastDFSClient;

    @PostMapping("/registerOrLogin")
    public BigHouseJSONResult registerOrLogin(@RequestBody User user) throws Exception {

        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return BigHouseJSONResult.errorMsg("用户名或密码不能为空...");
        }

        boolean isExist = service.userIsExist(user.getUsername());

        User result;
        if (isExist) {
            //登录
            result = service.getUserByUsernamePsw(user.getUsername(), MD5Utils.getMD5Str(user.getPassword()));
            if (result == null) {
                return BigHouseJSONResult.errorMsg("用户名或密码不正确...");
            }
        } else {
            //注册
            user.setNickname(user.getUsername());
            user.setFaceImage("");
            user.setFaceImageBig("");
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            result = service.saveUser(user);
        }

        return BigHouseJSONResult.ok(result);
    }

    @PostMapping("/uploadFaceBase64")
    public BigHouseJSONResult uploadFaceBase64(@RequestBody UserBO userBO) throws Exception {

        StorePath storePath = service.uploadFaceImg(userBO);
        // 带分组的路径
//        String url = storePath.getFullPath();
        // 不带分组的路径
        String url = storePath.getPath();
        // 获取缩略图路径
        String path = thumbImageConfig.getThumbImagePath(storePath.getPath());

        User user = new User();
        user.setId(userBO.getUserId());
        user.setFaceImage(path);
        user.setFaceImageBig(url);

        return BigHouseJSONResult.ok(service.updateUserImage(user));
    }

    @PostMapping("/setNickname")
    public BigHouseJSONResult setNickName(@RequestBody UserBO userBO) {

        User user = new User();
        user.setId(userBO.getUserId());
        user.setNickname(userBO.getNickname());
        return BigHouseJSONResult.ok(service.updateUserNickName(user));
    }

}
