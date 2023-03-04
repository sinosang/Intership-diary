# 2-22

- 上午

  ~~写bug~~

  

  重新配置了一个Springboot框架

  又碰到了jdk版本不匹配和jar包没有正常导入的问题

  花了点时间重新同步了一下java和sdk的版本

  重新下载，配置了maven的文件

  

  学习了部分Springboot框架的配置内容

  

   1.起步依赖

   spring-boot-starter-parent

   定义版本信息

   完成该功能需要的坐标

   工程继承parent，引入starter，通过依赖传递，简单方便的获取jar包

  

   2.配置文件分类

    默认配置文件为application
  
   application.yml / application.yaml
```
    - yml,yaml
    
      server:
          port:8080
    

    - properties
    
      server.port=8080
    

       设置端口信息
      
       同一级目录下，properties>yml>yaml
```



   3.yaml语法
```
properties
        
   server.port = 8080
   server.address=127.0.0.1
        
xml
        
<server>
     <port>8080</port>
     <address>127.0.0.1</address>
</server>
        
yml   要在:后带一个空格，通过缩进来识别

  server:
       port: 8080
       address: 127.0.0.1
        
```

​           1.区分大小写

​           2.数据前有空格作为分隔符

​           3.使用缩进表示层级关系

​           4.缩进不允许使用tab(可能由于不同系统的tab对应的空格数目不同)

​           5.缩进只要相同的层级元素左侧对齐即可

​           6.用#表示注释



4. yaml数据格式

   对象(MAP)，键值对的集合

   ```
   function1
   person:
      name: zhangsan
      
   function2
   person:{nanme: zhangsan}
   ```

   数组

   ```
   function1
   address:
      - beijing
      - shanghai 
   
   function2
   address: {beijing,shanghai}
   ```

   纯量(常量)

   单引号忽略转义字符

   双引号识别转义字符

   ```
   msg1: 'hello \n world'     
   msg2: "hello \n world"
   ```

   参数引用
   
   ```
   name: lilili
   
   person: ${name}    #引用上面的lilili
   ```
   
   

- 下午

5. 读取yml中的配置内容，获取数据

   

   使用@Value()
   
   读取配置文件application.yml中的内容

​       比较麻烦，一次只能调用一个

```
@Value("person.name")
private String name;    //调用yml中的person.name

@Value("${name}")
private String name2;    //调用yml中的name

```



​      使用@Autowired

​      使用getProperty()的方式来获取值

```
@Autowired
private Environment env;

System.out.println(env.getProperty("person.name"));
System.out.println(env.getProperty("person.age"));

```





6.  读取配置内容

​       通过调用Component，ConfigurationProperties的方式来进行调用

​       用Component来表示类

​       ConfigurationProperties(prefix = "person")

​      通过这种方式来定义COnfigurationProperties需要调用的是yml中的person

```
//Person 类
@Component
@ConfigurationProperties(prefix = "person")  //调用yml中person层级的内容

//Controller
@Autowired
private Person person;

System.out.println(person);


```



7.profile

通过调用profile功能来进行动态配置的切换

方便开发的时候程序部署在不同的环境中

开发，测试，生产等模式，配置的数据库地址，服务器端口等都不同

```
2.4版本以上就需要用config:，active:的方式来设置profiles

---
server:
   port: 8080

spring:
  config:
    active:
      on-profiles: dev
---
server:
   port: 8081

spring:
  config:
    active:
      on-profiles: test

---
server:
   port: 8082

spring:
  config:
    active:
      on-profiles:pro
---
spring:
  profiles:
    active: test
    
 
```



8.打包

IDEA右侧Maven

点击，刷新

在项目的Lifecycle中选择package，即可打包Sprinboot项目文件为jar包



感觉看的比较乱，明天打算从Maven开始学

预计接下来会从

Maven-->Mybaits/SSM-->Springboot

的方式开始学习



Maven

作为一个项目管理工具，将项目开发和管理过程抽象为一个项目对象模型(POM)

提供标准的，跨平台的自动化项目构建方式

统一了开发结构









































