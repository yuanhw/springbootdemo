## Spring boot 编程模式分享

### 相关资料

- 小马哥 Java 微服务实践 - Spring Boot 系列 https://www.bilibili.com/video/BV16J41197k2

- 小马哥 Java 微服务实践 - Spring cloud 系列 https://www.bilibili.com/video/BV1AJ411R73o

- 小马哥 mercyblitz《Spring Boot 编程思想》



### 1. Spring Boot 主要五大特性



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



- **SpringApplication** - 初始化、运行、结束、退出
- **嵌入式 Web 容器** - Servlet容器、Reactive Web容器
- **自动装配** -  spring 模式注解、@Enable 模块、条件装配、加载机制、Spring boot starter
- **外部化配置** - Environment抽象、生命周期
- **Production-readey** - 健康检查、数据指标、@Endpoint管控 Spring Boot Actuator

这五大特性构成了 Spring Boot 作为微服务中间件的基础，又提供了 Spring Cloud 的基础设施



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



### 3. FAT JAR 和 WAR 执行模块

### 4. 注解驱动编程

@Enable  

@Import

注解驱动

org.springframework.web.servlet.config.annotation.EnableWebMvc

接口编程

org.springframework.cache.annotation.EnableCaching

org.springframework.context.annotation.ImportSelector

org.springframework.context.annotation.ImportBeanDefinitionRegistrar



org.springframework.context.annotation.AnnotationConfigBeanDefinitionParser



### 5. Spring boot 自动装配

### 6. 理解 SpringApplication

### 7. 外部化配置