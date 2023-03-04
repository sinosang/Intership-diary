# 2-27

- 上午

- 看上周编写的实习日志，复习了一下之前学习过的技术内容

- Spring

  - 系统框架

    Data Access/integration   --   JDBC  --  数据访问和数据集成

    Web   ---  Web Socket,Servlet  --  Web开发

    AOP,Aspect  ---  面向切面编程，AOP思想的实现

    Core Container   --  核心容器

    Test -- 单元测试

    1. 核心容器(核心概念Ioc,DI)

    2. 整合(整合Mybatis)

    3. AOP(AOP基础操作)

    4. 事务

    5. 衍生(SpringMVC,SpringCloud)

  - IoC(inversion of control)

    对象的创建控制权由程序转移到外部，称为**控制反转**

    被创建或者被管理的对象在IoC中统称为Bean

  -  DI(Dependency Injection)依赖注入

    在容器中建立bean与bean之间的依赖关系





---



- 下午

  - 导入Spring坐标

  - 定义Spring管理的类

  - 创建Spring的配置文件，配置对应的类作为Spring管理的bean

  - 初始化IoC容器，通过容器获取bean

    ```
    bean标签配置
    id属性表示bean的名称,唯一
    如果配置了name,name为别名，可以为多个
    class表示bean的定义类型
    
    <bean id = "bookDao123" class="com.xxx.xxx.bookdao"></>
    
    service与dao关系
    property表示bean的属性
    name表示配置哪一个具体的属性的名称（private BookDao bookDao）
    ref表示需要关注的bean的id
    
    <bean>
       <property name="bookDao" ref="bookDao123">
    </bean>
    ```

  - 适用bean的作用范围

    表现层，业务层，数据层，工具对象

  - 不适用的

    封装实体的域对象

- bean声明周期



---



- 注解与反射

- 注射

  - 内置注解

    - @Override   重写的注解
    - @Deprecated  不推荐使用的注解
    - @SuppressWarnings("") 强制通过警告
    
  - 元注解   负责注释注解
  
    - @Target(value) 用于描述注解可以在什么地方被使用，@Target({ElementType.Type,ElementType.Method}),允许在类和方法中使用
  
    - @Retention()  用于描述注解的生命周期
  
      @Retention(RetentionPolicy.RUNTIME) 
  
    - @Inherited  子类可以继承父类的注解
  
  - 自定义注解
  
    - @interface 注解名称
    - 返回值的类型就是参数类型，class，string，enum
  
  - 注解可以赋值
  
- 反射

  - 通过反射获取类的Class对象

    ```
    Class c1 = Class.forName("com.test.reflection.learn");
    System.out.println(c1.hashCode());
    ```

  - 动态创建对象

    ```
    Class c = Class.forName("come.xxx.xxx.xxx"); 
    
    //构造一个对象
    //User user = (User)c1.newInstance();  //本质上调用了无参构造器
    //System.out.println(user);
    
    //通过构造器创建对象
    Constructor constructor = c.getDeclaredConstructor(String class,int class,int class);
    User user2 = (User)constructor.newInstance("ddd",001,123);
    System.out.println(user2);
    
    //通过反射调用方法
    User user3 = (User)constructor.newInstance();
    Method setName = c.getDeclaredConstructor("setName",String class,);
    setName.invoke(user3,dddlid)
    
    ```

    





---







# Summary

尝试学习了一下Spring的框架，了解框架中大致的组成和含义。但是在学习的时候对bean，依赖注入，注解等不是很明白。因此折返回去学习了一下注解和反射的概念和大致的意思。





























