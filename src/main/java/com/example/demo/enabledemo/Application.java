package com.example.demo.enabledemo;

import org.example.demo.enabledemo.EnableServer;
import org.example.demo.enabledemo.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * todo
 *
 * @author Wang Yuanhang
 * @date 2020/3/29 18:16
 */
@Configuration
@EnableServer(type = Server.type.FTP)
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(Application.class);

        context.refresh();

        Server server = context.getBean(Server.class);
        server.start();
        server.stop();
    }
}
