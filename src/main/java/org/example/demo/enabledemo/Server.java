package org.example.demo.enabledemo;

/**
 * todo
 *
 * @author Wang Yuanhang
 * @date 2020/3/29 18:29
 */
public interface Server {

    void start();

    void stop();

    enum type {
        HTTP, FTP
    }
}
