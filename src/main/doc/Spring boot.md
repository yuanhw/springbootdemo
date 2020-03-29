# Spring boot

## 一、 Spring boot 1.5

### 1. 监控

DevOps = Dev + Ops

Dev in spring boot = Services

Ops in spring boot = Management

Management = Endpoints

**Endpoints** = **监控**

- Jvm


- Metrics指标

`Web` 方式 和 `Jmx` 方式



### 2. 约定或习惯

`/META-INF/spring.factories`

`@ConfigurationProperties(prefix = "spring.mvc")`

`xxxProperties`

`XXXAutoConfiguration`



### 3. 安全

#### 默认配置

`org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter`

`org.springframework.security.config.annotation.web.builders.HttpSecurity`

> `WebSecurityConfigurerAdapter#configure(HttpSecurity)` 方法进行安全配置
>
> 默认如下：
>
> ```java
> http
> 		.authorizeRequests()
> 				.anyRequest().authenticated()
> 				.and()
> 		.formLogin().and()
> 		.httpBasic();
> ```



> `WebSecurityConfigurerAdapter#getHttp` 创建默认 `HttpSecurity` 
>
> 默认配置如下：
>
> ```java
> http
>   	.csrf() // 创建 CsrfConfigurer
>     .and()
>   	.addFilter(new WebAsyncManagerIntegrationFilter())
>     .exceptionHandling().and()
>     .headers().and()
>     .sessionManagement().and()
>     .securityContext().and()
>     .requestCache().and()
>     .anonymous().and()
>     .servletApi().and()
>     .apply(new DefaultLoginPageConfigurer<>()).and()
>     .logout();
> ```



#### Csrf 配置

> `org.springframework.security.web.csrf.CsrfFilter`
>
> `org.springframework.security.config.annotation.web.configurers.CsrfConfigurer#configure`
>
> `CsrfConfigurer` 创建 `CsrfFilter`
>
> ```java
> CsrfFilter filter = new CsrfFilter(this.csrfTokenRepository);
> RequestMatcher requireCsrfProtectionMatcher = getRequireCsrfProtectionMatcher();
> if (requireCsrfProtectionMatcher != null) {
>   filter.setRequireCsrfProtectionMatcher(requireCsrfProtectionMatcher);
> }
> ... 
> filter = postProcess(filter);
> http.addFilter(filter);
> ```



> Csrf 攻防逻辑
>
> `CsrfFilter#doFilterInternal`
>
> ```java
> // 1. 从 request 中获取 CsrfToken
> CsrfToken csrfToken = this.tokenRepository.loadToken(request);
> final boolean missingToken = csrfToken == null;
> // 2. 如果缺失，生成 CsrfToken 并且保存
> if (missingToken) {
>   csrfToken = this.tokenRepository.generateToken(request);
>   this.tokenRepository.saveToken(csrfToken, request, response);
> }
> // 3. 设置到 request属性 中
> request.setAttribute(CsrfToken.class.getName(), csrfToken);
> request.setAttribute(csrfToken.getParameterName(), csrfToken);
> // 4. 匹配需要校验的请求
> if (!this.requireCsrfProtectionMatcher.matches(request)) {
>   filterChain.doFilter(request, response);
>   return;
> }
> // 5. 从请求头或者请求参数中获取 token
> String actualToken = request.getHeader(csrfToken.getHeaderName());
> if (actualToken == null) {
>   actualToken = request.getParameter(csrfToken.getParameterName());
> }
> // 6. 校验
> if (!csrfToken.getToken().equals(actualToken)) {
>   // 失败处理
>   ...
>   return;
> }
> ```



#### 认证配置

> `WebSecurityConfigurerAdapter#configure(AuthenticationManagerBuilder)`
>
> `org.springframework.security.authentication.AuthenticationManager`
>
> `org.springframework.security.authentication.AuthenticationProvider`
>
> `org.springframework.security.core.userdetails.UserDetailsService`
>
> ```java
> // 继承 WebSecurityConfigurerAdapter
> ...
> // 配置 AuthenticationProvider
> @Override
> protected void configure(AuthenticationManagerBuilder auth) throws Exception {
>   auth.authenticationProvider(daoAuthenticationProvider(userDetailServiceImpl));
> }
> 
> // 使用 Dao 认证提供者
> @Bean
> public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailServiceImpl userDetailServiceImpl) {
>   DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
>   // 设置自定义的 UserDetailService 实现类
>   daoAuthenticationProvider.setUserDetailsService(userDetailServiceImpl);
>   // 设置 密码加密器
>   daoAuthenticationProvider.setPasswordEncoder(new PasswordEncoder() {
>     @Override
>     public String encode(CharSequence rawPassword) {
>       return rawPassword.toString();
>     }
> 
>     @Override
>     public boolean matches(CharSequence rawPassword, String encodedPassword) {
>       return encode(rawPassword).equals(encodedPassword);
>     }
>   });
>   return daoAuthenticationProvider;
> }
> 
> // 配置 HttpSecurity
> @Override
> protected void configure(HttpSecurity http) throws Exception {
>   http
>     .authorizeRequests().anyRequest().fullyAuthenticated()
>     .and()
>     .formLogin() // 表单登录
>     .loginPage("/user/login") // 登录页 url
>     .loginProcessingUrl("/user/doLogin") // 登录处理 url
>     .permitAll();
> }
> ```



#### 认证流程

> `org.springframework.security.web.context.SecurityContextPersistenceFilter`
>
> `org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter`
>
> `org.springframework.security.authentication.ProviderManager`
>
> `org.springframework.security.authentication.AuthenticationManager#authenticate`
>
> `AbstractAuthenticationProcessingFilter#successfulAuthentication`
>
> ```java
> // 获取认证信息
> SecurityContextHolder.getContext().getAuthentication();
> ```



### 4. 日志

### 5. JMX



## 二、Spring boot 2.0

### 核心特性

- **组件自动装配**：模式注解、@Enable模块、条件装配、加载机制

- **外部化配置**：Environment抽象、生命周期、破坏性变更

- **嵌入式容器**：Servlet容器、Reactive Web容器

- **Spring boot starter**：依赖管理、装配条件、装配顺序

- **Production-readey**：健康检查、数据指标、@Endpoint管控



### Spring boot 与 Java EE 规范

- **Web**：Servlet（JSR-315、JSR-340）
- **SQL**：JDBC（JSR-221）
- **数据校验**：Bean Validation（JSR-303、JSR-349）
- **缓存**：Java cache api（JSR-107）
- **Web sockets**：Java api for web socket （JSR-356）
- **Web services**：Jax-ws （JSR-224）
- **Java管理**：JMX（JSR 3）
- **Java消息**：JMS（JSR-914）



## 三、深度实践

### 1. 核心特性

**组件自动装配**

激活：`@EnableAutoConfiguration`

配置：`META-INF/spring.factories`

实现：`XXXAutoConfiguration`

**嵌入式 web 容器**

Web servlet : Tomcat、Jetty 和 undertow

Web Reactive :  Netty Web Server

**生产准备特性**

指标：/actuator/metrics

健康检查：/actuator/health

外部化配置：/actuator/configprops



### 2. Web相关

**传统 servlet 应用**

Servlet 组件：Servlet、Filter、Listener

`@WebServlet(urlPatterns = "/myServlet")`

Servlet 注册：Servlet注解、Spring Bean、RegisterationBean

`@ServletComponentScan(basePackages = "com.example.demo.web")`

异步非阻塞：异步 Servlet、非阻塞 Servlet

**Spring web mvc**

视图：模版引擎、内容协商、异常处理等

REST：资源服务、资源跨域、服务发现等

核心：核心架构、处理流程、核心组件等

**Web Server 应用**

切换其他 Server 容器

替换 Server 容器

自定义

`WebServerFactoryCustomizer`

`ReactiveWebServerFactoryCustomizer`



### 3. 数据相关

**Jdbc**

数据源

`javax.sql.Datasource`

JdbcTemplate

**JPA**

**事务**

Spring 事务抽象

`org.springframework.transaction.PlatformTransactionManager`

Jdbc 事务管理

`org.springframework.jdbc.datasource.DataSourceTransactionManager`

自动装配

`org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration`



### 4. 功能扩展

Spring boot 应用

`SpringApplication`

- 失败分析

  `org.springframework.boot.diagnostics.FailureAnalysisReporter`

- 应用特性

  Fluent API

- 事件监听

Spring boot 配置

外部化配置、profile、配置属性

`org.springframework.boot.context.properties.ConfigurationProperties`

`@Profile`

`org.springframework.core.env.PropertySource`

Spring boot starter

Starter 开发、最佳实践

### 5. 运维管理

Spring Boot Actuator

- 端点：各类 Web 和 JMX Endpoints
- 健康检查：Health 和 HealthIndicator
- 指标：内建 Metrics、自定义 Metrics

### 6. 总结

