# 2-24

- 上午

  - Mybatis入门

    - 手动导入

    - resources创建mybatis-config的xml核心配置文件，在其中配置数据库的连接信息和sql的映射文件

    - resources创建TestMapper的xml映射文件，定义namespace名称空间，统一管理sql语句

    - 创建POJO类

    - 加载核心配置文件，获取SqlSessionFactory对象

      ```
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resource.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      ```

    - 获取SqlSession的对象

      ```
      SqlSession sqlSession = sqlSessionFactory.openSession();
      ```

    - 执行sql语句

      ```
      List<User> users = sqlSession.selectList("test.selectBlog");
      System.out.println(users)
      ```

    - 释放资源

      ```
      sqlSession.close();
      ```

  - 用IDEA配置了一下Database，在IDEA中连接了数据库，直接使用IDEA编写了SQL的语句，操作数据库。

  - Mapper代理开发

    ~~写BUG~~

  - 尝试使用Mybatis编写完整的增删查改功能



---



- 下午

  ~~又写了一段时间Mapper代理的Bug~~

  应该是由于没有严格按照规定设置层级文件，导致的bug

  ```
  层级文件
  src
    main 
      java
        com
        | test
        |   mapper
        |     UserMapper   ---Mapper接口
        |   pojo
        |     User         ---实体类
        Mybatis_demo
        Mybatis_demo2      ---编码
      resource
        com
          test
            mapper
              UserMapper.xml     ---映射文件
            mybatis-config.xml   ---核心配置
  ```

  

  1. 定义和SQL映射文件与**同名**的Mapper的接口，将Mapper和SQL映射文件置于**同一个目录层级**下
  2. 设置SQL映射文件的namespace属性为Mapper接口的路径
  3. 在Mapper的接口定义方法，方法名为映射文件中sql的**id**，参数类型和返回值一致(实体类的路径)
  4. 更改核心配置中的mapper路径
  5. 编码

  

  区别于selectList，使用Mapper在执行sql语句的时候

  ```
  UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
  List<User> users = userMapper.selectAll();
  System.out.println(users);
  ```

  

  - Mybatis查看指定id

    参数占位符

    - #{}：将其转换为？，防止SQL注入
    - %{}：拼接SQL，存在SQL注入问题

    ```
    映射文件
    SELECT xxxxx where id = #{id};
    
    interface
    User selectByid(int id);
    
    entity
    User user = userMapper.select(1);
    ```

  - Mybatis多条件查询

    使用map封装对象
    
  - Mybatis添加
  
    ```
    需要手动事务提交commit
    或者在sqlSession中设置自动提交事务
    ```
  
  - Mybatis修改
  
    ```
    使用动态修改的方式
    值不为空，且不为null的时候才进行update
    否则不更新
    ```
  
  - Mybatis删除
  
  - Mybatis批量删除
  
  - 使用注解的方式进行增删改查
  
    一般用于完成一些简单的功能，复杂的功能使用XML进行映射更好(之前写的那些)
  
  

## Summary

基本学习了Mybatis的使用，能够进行基本的增删查改以及一些复杂的操作。主要是在Mapper配置代理的时候因为层级和路径等原因浪费了太多的时间。



  

  

  