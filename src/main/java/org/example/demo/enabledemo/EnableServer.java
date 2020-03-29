package org.example.demo.enabledemo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

import static org.example.demo.enabledemo.Server.type.HTTP;

/**
 * todo
 *
 * @author Wang Yuanhang
 * @date 2020/3/29 18:31
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
// 基于“接口编程”实现 @Enable 模块
//@Import(ServerImportSelector.class)
@Import(ServerImportBeanDefinitionRegistrar.class)
public @interface EnableServer {

    Server.type type() default HTTP;

}
