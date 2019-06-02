package com.bighouse.controller;

import com.bighouse.pojo.User;
import com.bighouse.pojo.vo.Uservo;
import com.bighouse.service.UserService;
import com.bighouse.utils.BigHouseJSONResult;
import com.bighouse.utils.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("u")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/registerOrLogin")
    public BigHouseJSONResult registerOrLogin(@RequestBody User user) throws Exception {

        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return BigHouseJSONResult.errorMsg("用户名或密码不能为空...");
        }

        boolean isExist = service.userIsExist(user.getUsername());

        User result = null;
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
        Uservo uservo = new Uservo();
        BeanUtils.copyProperties(result,uservo);

        return BigHouseJSONResult.ok(uservo);
    }
}
