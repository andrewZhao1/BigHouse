package com.bighouse;

import com.bighouse.netty.WSServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class NettyBooter implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        try {
            WSServer.getInstance().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
