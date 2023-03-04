# 2-28

- 上午

- Spring中的常用注解

  **使用的时候需要开启属性注解支持，并且在Spring配置文件中引入context文件头**

  - 属性注入

    @Value("sino")

    ```
    public class User{
    @Value("sino")
    //相当于在application.xml中的<property name = "name" value="sino">
    public String name;
    }
    ```

  - 声明Bean的注解 --- 以下几个的功能都一致

    1. @Component   

       ```
       @Component("user")
       // 相当于配置文件中 <bean id="user" class="当前注解的类"/>
       public class User {
          @Value("sino")
          // 相当于配置文件中 <property name="name" value="sino"/>
          public String name;
       }
       ```

       泛化的概念，仅表示Bean对象，可以作用与任何层次

    2. @Repository --- 持久层的注解(DAO)

    3. @Service --- 业务层的注解(Service)

    4. @Controller --- 表现层的注解(Contoller)

  - 注入Bean的注解

    1. @Autowired
  
       可以对类的成员变量，方法以及构造方法进行标注，完成自动装配。@Autowired的使用来消除setter和getter的方法，根据byType查找，按照Bean类型装配
  
       ```
       public class User {
          @Autowired
          private Cat cat;
          @Autowired
          private Dog dog;
       
          public Cat getCat() {
              return cat;
         }
          public Dog getDog() {
              return dog;
         }
       }
       ```
    
    2. @Qualifier
    
       根据byName的方式进行自动装配，不能单独使用
    
       ```
       <bean id="dog1" class="com.kuang.pojo.Dog"/>
       <bean id="dog2" class="com.kuang.pojo.Dog"/>
       <bean id="cat1" class="com.kuang.pojo.Cat"/>
       <bean id="cat2" class="com.kuang.pojo.Cat"/>
       ```
    
       由于类名不同，此时只是用@Autowired会报错
    
       ```
       @Autowired
       @Qualifier(value = "cat2")
       private Cat cat;
       @Autowired
       @Qualifier(value = "dog2")
       private Dog dog;
       ```
       
    3. @Resource
    
       现根据byName查找，不成功再根据byType方式装配
    
       ```
       applications.xml:
       <bean id="dog" class="com.kuang.pojo.Dog"/>
       <bean id="cat1" class="com.kuang.pojo.Cat"/>
       <bean id="cat2" class="com.kuang.pojo.Cat"/>
       
       <bean id="user" class="com.kuang.pojo.User"/>
       ```
    
       ```
       实体类;
       public class User {
          //如果允许对象为null，设置required = false,默认为true
          @Resource(name = "cat2")
          private Cat cat;
          @Resource
          private Dog dog;
          private String str;
       }
       ```
    
    **最佳实践：xml与注解的整合开发**
    
    xml管理Bean，注解完成属性的注入
    
    
    
---

​    

- 代理模式

  AOP的底层机制就是动态代理

  - 静态代理

    方便真实角色，Client更纯粹

    但是当类多了，多了代理类，工作量变大，开发效率降低

  - 动态代理

    公共业务由代理完成，实现了业务的分工

    公共业务发生扩展的时候可以更加集中方便




---



- 下午

- AOP

  面向切面的编程，作为OOP的延续，降低不同的业务逻辑各部分耦合度，提高程序可重用性。

  - 通过Advice定义的横切逻辑，支持的Advice

    | 注解名称 | 通知类型 | 描述 |
    | --- | -------- | :-------: |
    | @Aspect | none | 用于定义一个切面，注解在切面上 |
    | @Before | 前置通知 | Before("execution(* com.ku.service.user.*(..))")，用于权限管理 |
    | @Around | 环绕通知 | Around("execution(* com.xxx.xxx.*(..))"),用于日志记录，事务处理 |
    | @After         | 后置通知 | 方法执行后实施，不论成功与否，用于释放资源 |
    | @AfterThrowing | 异常通知 | 方法异常后实施增强，用于异常处理，日志记录                   |
    | @Pointcut      | 引入通知 | 用于修改目标类，增强类                                       |
    
    ```
    "execution(* aspecj.dao.*.*(..))"  ---  定义切入点表达式
    该句表示匹配aspectj.dao包中任意类的任意方法执行
    
    execution为表达式的主体
    第一个*表示返回类型，*表示返回所有类型
    第二个*表示类名，*表示匹配所有的类
    第三个*表示方法名，*表示所有的方法
    (..)表示方法的参数，..表示任意参数
    ```
    
    example:
    
    ```
    @Aspect  //声明一个Aspect切面
    @Component  //让此切面成为Spring容器管理的Bean
    public class MyAspect {
      
      @Pointcut("execution(* aspectj.dao.*.*(..))")  //定义切入点表达式
      private void mypointcut(){}
      
      @Before("mypointcut")  //mypoint()为切入点的定义方法
      public void before(){
      System.out.println("前置通知")；
      }
      
      @After("mypointcut")
      public void afater(){
      System.out.println("后置通知")；
      }
      @Around("mypointcut")
      public void around(ProceedingJoinPoint jp) throw Throwable{
      System.out.println("环绕前通知")；
      Object proceed = jp.proceed();  //执行方法
      System.out.println("环绕后通知")
      }
    }
    ```
    
    result：
    
    ```
    环绕前开始 --》 前置通知 --》 方法 --》 后置通知 --》 环绕后通知
    ```
    
    

---



- SpringMVC

  基于Java实现的MVC模型的轻量级的Web框架

  以Controller作为业务的核心，包含对用户请求的处理逻辑

  - 定义使用Controller，用@Controller来定义Bean
  - @RequestMapping("/hello")来设置操作的访问路径
  - @ResponseBody来设置操作的返回类型

  



---







## Summary

大致学习完了Spring，主要是对Spring中注解的了解和学习。然后开始尝试学习一下SpringMVC的架构思想。