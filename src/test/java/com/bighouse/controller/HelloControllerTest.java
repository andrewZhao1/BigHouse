package com.bighouse.controller;


import com.bighouse.MessageGroupApplication;
import com.bighouse.service.MyFriendsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessageGroupApplication.class)
public class HelloControllerTest {

    @Autowired
    private MyFriendsService service;

    @Test
    public void selectAll(){
        System.out.println("-----------------------------");
        System.out.println(service.selectAll());
    }
}
