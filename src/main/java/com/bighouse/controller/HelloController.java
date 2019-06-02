package com.bighouse.controller;

import com.bighouse.pojo.MyFriends;
import com.bighouse.service.MyFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private MyFriendsService service;

    @RequestMapping("/hello")
    public String hello(){
        return "hello bie house";
    }


    @RequestMapping("/selectAllFriends")
    public List<MyFriends> selectAll(){
        return service.selectAll();
    }
}
