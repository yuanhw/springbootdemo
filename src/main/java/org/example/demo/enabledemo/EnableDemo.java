package org.example.demo.enabledemo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * todo
 *
 * @author Wang Yuanhang
 * @date 2020/3/29 18:06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
// 基于“注解驱动”实现 @Enable 模块
@Import(DemoConfiguration.class)
public @interface EnableDemo {
}
