# 2-23

- 上午

- Maven初级

  - Maven坐标

    ```
    groupId:定义当前Maven项目隶属的组织名称(通产为域名的反写)
    artifactId:定义当前Maven项目的名称(模块名称)
    version:版本号
    packaging:定义该项目的打包方式
    ```

  - 其他的一些坐标

    ```
    dependencies：设置当前项目工程的所有依赖
    dependency：依赖具体
    
    ---打插件
    build：构建
    plungins：设置插件
    plungin：具体的插件配置
    
    ---排除依赖---
    exclusions：排除哪一些依赖
    exclusion：具体的依赖
    
    ---可选依赖---
    optional：隐藏依赖
    ```

  - 依赖管理

    - 路径优先：依赖中出现相同的资源，层级越深，优先级越低
    - 声明优先：资源在同一层级被被依赖，配置靠前的会覆盖顺序靠后的
    - 特殊优先：同一级配置相同资源的不同版本，后配置的覆盖前配置的

  - 依赖范围

    - 主程序范围有效(main文件内)
    - 测试程序范围有效(test文件内)
    - 是否参与打包(package范围内)

  - 项目的生命周期

    clean：清理工作

    default：核心工作，例如编译，测试，打包，部署等等

    site：生产报告，发布站点

  - 插件

    插件与声明周期内的阶段相互绑定，执行到对应的生命周期执行对应的功能

    默认在maven的生命周期上绑定预设功能

    通过插件可以自定义其他功能

    

---



- Maven高级

   **打算学习SSM之后再回来巩固看一下**

  - 分模块开发，设计

    ```
    .idea
    src
       main
          java
             com.xxxxx
                controller
                dao
                domain
                service
                system.exception
             respirces
             webapp
          test
             java
             resources
       target
    ```



---



- JDBC

  用Java来操作关系型数据库的API

  大致分为

  - 注册驱动(可以省略)

    ```
    Class.forName("com.mysql.cj.jdbc.Driver");
    ```

  - 获取连接

    ```
    String url = "jdbc:mysql://127.0.0.1:3306/数据库"；
    String username = "root";
    String password = "xxxxx";
    Connection conn = DriverMnager.getConnection(url,username,password);
    ```
    
  - 定义SQL语句
  
    ```
    String sql = "update jdbc_Test.newtable set money = 1000 where id = 1;";
    ```
    
  - 获取SQL对象
  
    ```
    Statement stmt = conn.createStatement();
    ```
  
  - 执行SQL
  
    ```
    int count = stmt.executeUpdate(sql);
    ```
  
  - 处理了几行                    
  
    ```
    System.out.println(count);
    ```
  
  - 释放资源
  
    ```
    stmt.close();
    conn.close();
    ```
  



---



- 下午
  
- JDBC的部分API
  
  - DriverManager
  
    注册驱动，获取数据库连接
  
    如果连接本机的Mysql，端口为3306，可以简写为
  
    ```
    String url = "jdbc:mysql:///数据库";
    Connection conn = DriverManager.getConnection(url,username,password);
    ```
  
  - Connection
  
    获取执行的SQL对象，管理事务
  
    普通执行SQL对象
  
    ```
    Statement stmt = conn.createStatement();
    ```
  
    事务管理
  
    **在执行SQL之前开启事务，处理结果之后结束事务**
  
    ```
      //获取SQL对象
    try{
      conn.setAutoCommit(false);    #开启事务,true自动，false手动
      //执行SQL
      //处理结果
      conn.commit();       #提交事务 
    }catch(Exception throwables){
      conn.rollback();     #回滚事务
      throwables.printStackTrace();   
    }
    ```
  
  - Statement      ---demo
  
    执行SQL语句
  
    - executeUpdate(sql)   执行DML,DDL语句
  
      对表，库进行增，删，改
  
    ```
    int count = stmt.executeUpdate(sql);
    ```
  
  - ResultSet      ---demo1
  
    封装了DQL查询语句的结果
  
    - executeQuery(sql)    执行DQL
  
      对表，库进行查找
  
    ```
    Result rs = stmt.executeQuery(sql);
    ```
  
  - PrepareStatement    ---demo2
  
    预编译Sql并执行Sql语句
  
    ```
    String sql = "select * from xxxx where name = ?"
    
    PrepareStatement pstmt = conn.prepareStatement(sql);
    
    pstmt.setString(1,name);   #定义sql中的？
    
    ResultSet rs=pstmt.executeQuery();
    ```
  
    

---



- Mybatis

  免除了几乎所有的JDBC代码，以及设置参数和获取结果的工作。

  









## Summary

学习了Maven的配置，作用。了解了Springboot框架中yml文件里各个部分标签所代表的意义，一并学习了依赖管理和配置。原定打算在学习了Maven之后开始学习Mybaits，考虑到对SSM框架的掌握还不够熟练，决定先看一下JavaWeb方向的内容。从JDBC入手，了解Mybaits的底层逻辑。预计在学习了JDBC后，从明天开始Mybaits的学习。













