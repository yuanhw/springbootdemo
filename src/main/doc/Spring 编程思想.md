## Spring boot 编程思想

## 一、总揽 Spring Boot

### 1. 创建

#### 命令行方式

```shell
# maven 命令创建项目原型 interactiveMode 交互模式
mvn archetype:generate -DgroupId=com.example -DartifactId=d1 -Dversion=1.0.0-SNAPSHOT -DinteractiveMode=false

# maven 打包
mvn clean package -DskipTests=true

# maven 查看依赖
mvn dependency:tree -Dincludes=org.springframework*
```



#### 图形化方式

```properties
# 快速创建 Spring boot 应用的网站
https://start.spring.io
```



#### 创建可执行 JAR

```properties
# Fat JAR 采用 zip 压缩格式存储
# 执行 repackage 目标
<plugin>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
```



#### 错误分析报告器

```java
org.springframework.boot.diagnostics.FailureAnalysisReporter
```



#### 执行模块 

`spring-boot-loader`

```properties
# java -jar 启动类必须配置在 /META-INF/MANIFEST.MF 的 Main-Class 属性中
# JarLauncher 会将 /BOOT-INF/lib 目录下的文件作为 Start-Class 类的类库依赖
Main-Class: org.springframework.boot.loader.JarLauncher
Start-Class: xxx.xxx.xxx

org.springframework.boot.loader.Launcher
-- org.springframework.boot.loader.ExecutableArchiveLauncher
-- -- org.springframework.boot.loader.JarLauncher
-- -- org.springframework.boot.loader.WarLauncher

org.springframework.boot.loader.archive.Archive.Entry
-- org.springframework.boot.loader.archive.JarFileArchive.JarFileEntry
-- -- org.springframework.boot.loader.archive.ExplodedArchive.FileEntry
```



### 2. 理解固化的 Maven 依赖

`spring-boot-starter-parent`

`spring-boot-dependencies`

#### 单独导入

```properties
# 依赖管理
<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-parent</artifactId>
				<version>2.2.4.RELEASE</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
</dependencyManagement>

# 插件
<build>
		<plugins>
		  <!-- war 包插件 -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<version>3.1.0</version>
			</plugin>

			<!-- spring-boot 插件 -->
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<version>2.0.2.RELEASE</version>
				<executions>
					<execution>
						<goals>
							<goal>repackage</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
		</plugins>
</build>
```



### 3. 嵌入式 web 容器

- Servlet 容器
  - Tomcat
  - Jetty
  - Undertow
- 非 Servlet 容器 
  - Reactive Web 容器
    - Netty Web Server
    - Servlet 3.1+ 满足异步非阻塞特性



| Servlet 规范      | Tomcat | Jetty | Undertow |
| ----------------- | ------ | ----- | -------- |
| 4.0               | 9.x    | 9.x   | 2.x      |
| 3.1（异步非阻塞） | 8.x    | 8.x   | 1.x      |
| 3.0               | 7.x    | 7.x   |          |

Spring boot 嵌入式 web 容器接口

```properties
# spring boot 1.x
org.springframework.boot.context.embedded.EmbeddedServletContainer

# spring boot 2.x
org.springframework.boot.web.server.WebServer
```



#### 嵌入式 Reactive Web 容器

`spring-boot-starter-webflux`

和`spring-boot-starter-web`并存的情况下前者被忽略

```properties
# 默认 netty web server
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webflux</artifactId>
</dependency>

# 用 tomcat 替换 netty
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
</dependency>

# 用 undertow 替换 netty
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-undertow</artifactId>
</dependency>
```



```properties
# run(ApplicationArguments) 方法在应用启动后回调
org.springframework.boot.ApplicationRunner
# web server 应用上下文
org.springframework.boot.web.context.WebServerApplicationContext

# web server 已初始化事件
org.springframework.boot.web.context.WebServerInitializedEvent

```

```java
// 打印出 WebServer 类型
@Bean
public ApplicationRunner runner(WebServerApplicationContext webServerApplicationContext) {
		return args -> log.info("Web server name : {}", webServerApplicationContext.getWebServer().getClass().getName());
}

@EventListener(WebServerInitializedEvent.class)
public void onWebServerReady(WebServerInitializedEvent event) {
		log.info("Web server class name : {}", event.getWebServer().getClass().getName());
}
```



### 4. 理解自动装配

#### `@SpringBootApplication`

多层次 `@Component` 派生性

`@Component`

- `@Configuration`
- - `@SpringBootConfiguration`

Spring 模式注解



> `@SpringBootApplication`
>
> 相当于 `@ComponentScan` ` @Configuration` `@EnableAutoConfiguration` 的作用



#### `@SpringBootApplication` 属性别名

`@AliasFor`



#### @SpringBootApplication “继承” @Configuration CGLIB 提升特性

轻量模式 和 完全模式



> `@Configuration` 中的 `@Bean` 方法 CGLIB 提升  



### 5. Spring Boot Actuator

用于监控和管理 Spring 应用，通过下面两种方式：

- JMX Bean
- HTTP Endpoint

#### Endpoints

```properties
management.endpoints.web.exposure.include = *
```



#### 外部化配置

`PropertySource` 外部化配置属性源

三种用途：

- Bean 的 `@Value` 注入
- Spring `Environment` 读取
- `@ConfigurationProperties` 绑定到对象



#### 规约大于配置



### 6. 小结

Spring Boot 主要五大特性：

- SpringApplication
- 自动装配
- 外部化配置
- Spring Boot Actuator
- 嵌入式 Web 容器

这五大特性构成了 Spring Boot 作为微服务中间件的基础，又提供了 Spring Cloud 的基础设施





## 二、走向自动装配



### 1. 注解驱动开发

##### 注解编程模型

- 元注解

- Spring 模式注解
- - @Component 多层次派生性
- Spring 组合注解
- Spring 注解属性别名和覆盖



元素 XML Schema 命名空间需要与其处理类建立映射关系

```properties
/META-INF/spring.handlers

org.springframework.context.config.ContextNamespaceHandler
org.springframework.context.annotation.ComponentScanBeanDefinitionParser#parse()
org.springframework.context.annotation.ClassPathBeanDefinitionScanner#doScan()

org.springframework.core.type.AnnotationMetadata
org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor (ASM)
org.springframework.core.type.StandardAnnotationMetadata (Java 反射)
```



隐式覆盖 和 显示覆盖

@AliasFor 同注解属性相互标注，值必须相同