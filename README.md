Spring-Boot-Security-Example
============================    

This project is a combination of spring boot rest web services, spring-security and spring boot jar to war implementations using Java Springs, Hibernate and MySQL. I decided to add this to public github because it took me some effort to get things running as there are limited examples of Spring Boot available. Also while since most examples on internet as of today use standard xml configurations for servlet mapping, hibernate configurations etc., it was difficult to map those in Spring Boot as it used annotations for basically everything. 

This project can serve as a template or starting point as it contains both of a simple webservice and spring mvc. Find below some projects that I referenced while creating this template example. 

- https://spring.io/guides/gs/rest-service/

- https://spring.io/guides/gs/convert-jar-to-war/

- https://github.com/Fruzenshtein/security-spr

---

# Other Dependencies (Apart from code here)



##Creating tables in mysql  

  
#### Create a basic table to try simple add user request using Spring Boot, Hibernate and MySQl.

    mysql> CREATE TABLE `USER_DETAILS` (
      `id` int(6) NOT NULL AUTO_INCREMENT,
      `uuid` varchar(40) NOT NULL,
      `email` varchar(40) NOT NULL,
      PRIMARY KEY (`id`)
    );
    
---

#### Create below tables to try spring security using Admin roles and Admin users.

    mysql> CREATE TABLE `ADMIN_ROLES` (
      `id` int(6) NOT NULL AUTO_INCREMENT,
      `role` varchar(20) NOT NULL,
      PRIMARY KEY (`id`)
    );

    mysql> CREATE TABLE `ADMIN_USERS` (
      `id` int(6) NOT NULL AUTO_INCREMENT,
      `login` varchar(20) NOT NULL,
      `password` varchar(20) NOT NULL,
      PRIMARY KEY (`id`)
    ); 

    //This table is used to join/map ADMIN _USERS and ADMIN_ROLES 
    mysql> CREATE TABLE `USER_ROLES` (
      `user_id` int(6) NOT NULL,
      `role_id` int(6) NOT NULL,
      KEY `user` (`user_id`),
      KEY `role` (`role_id`)
    );

    mysql> INSERT INTO ADMIN_ROLES (role) VALUES ('ADMIN'), ('MODERATOR');
    
    mysql> INSERT INTO ADMIN_USERS (login, password) VALUES ('mod', 'mod123'), ('admin', 'admin123');
    
    mysql> INSERT INTO USER_ROLES (user_id, role_id) VALUES (1, 2), (2, 1);

##	修改Hibernate dao 支持 spring data jpa.
新增
	compile 'org.springframework.data:spring-data-jpa:1.8.0.RELEASE'
新增
	spring security 支持预先授权 	
修改   
	spring boot 数据源配置
新增
	sessionRegistry 防止一个用户只有一个session登录
新增----20160108
	实体类管理
	session 失效时间
	
修改----20160108
sping.data.jpa支持   ;去掉hibernate管理实体类 ; 去掉security预授权的支持 http.httpBasic().realmName("admin");
		
新增 ----20160111
	封装RestTemplate和AsyncRestTemplate工具的封装   
	新增Spring RestTemplate和AsyncRestTemplate基本验签功能。
	完善Rest工具，重写createRequest(URI uri, HttpMethod httpMethod)方法，添加权限头信息和重写URI参数添加baseUrl路径；
	完善Rest工具，重写setRequestFactory方法,添加超时处理；
	修复demo中调用今日作业后台service时，返回的javax.ws.rs.core.Response时，不能序列化抽象类问题。实际取值是Response.ok.Entity(resultType)的中resultType类型
	
新增----20160115
	优化AsyncRestTemplate工具的封装
	引入httpClient和httpClient NIO
	
修改----20160118
	优化AsyncRestTemplate工具的超时处理	
	
修改----20160119
	修改RestTempalte和AsyncRestTemplate的覆盖RequestFactory类型修改为HttpComponentsAsyncClientHttpRequestFactory
	
新增-----20160120
	Springmmvc基于实体类提交，和实体验证工具封装。	
	参考：
	javax.validation.Valid 和 javax.validation.constraints.NotNull
	javax.validation.constraints.Pattern、java.util.regex.Pattern、org.hibernate.validator.internal.constraintvalidators.PatternValidator
	
