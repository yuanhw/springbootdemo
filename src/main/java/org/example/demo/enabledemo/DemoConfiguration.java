package org.example.demo.enabledemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * todo
 *
 * @author Wang Yuanhang
 * @date 2020/3/29 18:08
 */
@Configuration
public class DemoConfiguration {

    @Bean
    public Demo demo() {
        return new Demo();
    }
}
