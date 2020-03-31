## Spring boot 编程模式分享

### 相关资料

- 小马哥 Java 微服务实践 - Spring Boot 系列 https://www.bilibili.com/video/BV16J41197k2

- 小马哥 Java 微服务实践 - Spring cloud 系列 https://www.bilibili.com/video/BV1AJ411R73o

- 小马哥 mercyblitz《Spring Boot 编程思想》



> Spring Framework 颠覆了J2EE的开发模式，依赖于新的概念 `Ioc` 和 `DI` 。实际上，在 Java EE 技术体系上，`IOC` 的实现方式是 `JNDI` ，而 `DI` 是 `EJB` 容器注入。通过了解 Java 标准 也就是 `JSR` ，发现 Spring Framework 也是在重复发明轮子。
>
> Spring Framework 对事物的抽象，统一了数据库事务和分布式事务，利用 `AOP` 使事务编程极简化。Spring Web Mvc ，使整体框架的发展达到顶峰。
>
> 但是，由于它并非容器，必须随 Java EE 容器启动装载，所以无法摆脱被动的境况。Spring boot 的出现改变了这个情况。
>
> Spring boot 具有一套固化的视图，用于构建生产级别的应用。具备开发和运维双重特性，是目前非常流行的微服务架构。
>
> 
>
> 从 dev 和 ops 维度看 spring boot
>
> DevOps = Dev + Ops
>
> Dev in spring boot = Services
>
> Ops in spring boot = Management
>
> Management = Endpoints = 监控
>
> `Web` 方式 和 `Jmx` 方式



### 1. Spring Boot 主要六大特性 Features

- **创建独立的 Spring 应用**

- **嵌入式 Web 容器** - Servlet容器、Reactive Web容器
- **提供固化的 `starter` 依赖，简化构建配置**
- **自动装配** -  spring 模式注解、@Enable 模块、条件装配、加载机制、Spring boot starter
- **Production-readey** - 健康检查、数据指标、外部化配置、@Endpoint管控 Spring Boot Actuator
- **不需要 XML 配置**

五大特性构成了 Spring Boot 作为微服务中间件的基础，又提供了 Spring Cloud 的基础设施

https://spring.io/projects/spring-boot

https://docs.spring.io/spring-boot/docs/2.0.2.RELEASE/reference/htmlsingle/



#### 独立的 Spring 应用

```properties
# The Executable Jar Format
# Jar 有时称为 FAT JAR，采用 zip 压缩格式存储

# 1. 可执行 JAR
# 2. 可执行 JAR 资源结构
# 3. FAT JAR 执行模块

# xxx.jar 和 xxx.jar.original 的区别
unzip xxx.jar -d xxx
tree xxx/

cat /META-INF/MANIFEST.MF 
# Main-Class
# 查找依赖 https://search.maven.org/classic/

代码分析：
org.springframework.boot.loader.JarLauncher
```



#### 提供固化的 `maven` 依赖

```properties


```



### 2. Spring boot 与 Java EE 规范

- **Web**：Servlet（JSR-315、JSR-340）
- **SQL**：JDBC（JSR-221）
- **数据校验**：Bean Validation（JSR-303、JSR-349）
- **缓存**：Java cache api（JSR-107）
- **Web sockets**：Java api for web socket （JSR-356）
- **Web services**：Jax-ws （JSR-224）
- **Java管理**：JMX（JSR 3）
- **Java消息**：JMS（JSR-914）
- **...**

JSR 文档 https://github.com/mercyblitz/jsr.git



### 3. 注解驱动编程

@Enable  

@Import

注解驱动

org.springframework.web.servlet.config.annotation.EnableWebMvc

接口编程

org.springframework.cache.annotation.EnableCaching

org.springframework.context.annotation.ImportSelector

org.springframework.context.annotation.ImportBeanDefinitionRegistrar



org.springframework.context.annotation.AnnotationConfigBeanDefinitionParser



### 4. Spring boot 自动装配

### 5. SpringApplication 分析

### 6. 外部化配置