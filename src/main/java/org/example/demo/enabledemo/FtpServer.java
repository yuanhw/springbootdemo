package org.example.demo.enabledemo;

/**
 * todo
 *
 * @author Wang Yuanhang
 * @date 2020/3/29 18:29
 */
public class FtpServer implements Server {

    @Override
    public void start() {
        System.err.println("ftp server start ...");
    }

    @Override
    public void stop() {
        System.err.println("ftp server stop ...");
    }
}
