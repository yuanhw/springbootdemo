package com.example.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlusConfiguration
 *
 * @author Wang Yuanhang
 * @date 2020/3/21 1:33 下午
 */
@Configuration
@MapperScan(basePackages = "com.example.demo.mapper")
public class MybatisPlusConfiguration {
}
