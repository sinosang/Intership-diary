# 3-2

- 上午

- MybatisPlus

  - 标准数据层CRUD

    | 功能       | 自定义的接口                              | MP接口                                             |
    | :--------- | :---------------------------------------- | :------------------------------------------------- |
    | 新增       | boolean save(T t)                         | int insert (T t)                                   |
    | 删除       | boolean delete(int id)                    | int deleteByid(Serializable id)                    |
    | 修改       | boolean update(T t)                       | int updateByid ( T t)                              |
    | 根据id查询 | T getId(int id)                           | T selectByid(Serializable id)                      |
    | 查询全部   | List < T > getAll()                       | List < T > selectList()                            |
    | 分页查询   | PageInfo < T > get All(int page,int size) | IPage < T > selectPage(IPage < T> page)            |
    | 按条件查询 | List < T > getAll ( Condition condition)  | IPage < T > selectPage(Wrapper < T >  queryWrapper |
    
  - 配置环境
  
  - CRUD操作
  
    学习了如何使用MybatisPlus可以直接节省部分代码的编写



---



- 下午

  - 配环境...

  - 分页

    设置分页拦截器作为Spring的bean

    ```
    @Configuration
    public class MPconfig{
       @Bean
       public MybatisPlusInterceptor pageInterceptor(){
          MybatisPlusInterceptor interceptor = new Mybatis PlusIntercepter();
          interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
          return interceptor;
       }
    }
    ```

    执行分页查询

    ```
    void testGetPage(){
      IPage page = new Page(1,2);
      userDao.selectPage(page,null);
      System.out.println("now page:"+ page.getCurrent());
      System.out.println("show page size:"+ page.getSize());
      System.out.println("all page :"+ page.getPages());
      System.out.println("all data :"+ page.getTotal());
      System.out.println("data :"+ page.getRecord());
    }
    ```

    开启日志

    ```
    mybatis-plus:
       confiuration:
          log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    ```

  - id生成策略

    ```
    @TableId(type = IdType.AUTO)
    //自动生成的id
    
    @TableId(type = IdType.INPUT)
    //用户手动输入的id
    
    @TableId(type = IdType.ASSIGN_ID)
    //使用雪花算法生成用户的ID，避免水平多表重复ID出现
    
    @TableId(type = IdType.UUID)
    //使用UUID生成算法作为id测率
    
    ```

  - 多数据删除操作

    ```
    List <long> ids = Arrays.asList(new Long[]{2,3}); 
    
    userDao.deletBatchIds(ids);
    ```

  - 多数据查询操作

    ```
    List <long> ids = Arrays.asList(new Long[]{2,3}); 
    
    List <User> userList = userDao.selectBatchIds(ids);
    ```



---



- Springboot

  - parent

  - 整合了一下Junit

    用classes来引导类

    - 如果测试类存在于引导类所在的包或者子包中，则无需指定引导类
    - 测试类如果不存在于引导类所在的包或者子包中，需要通过classes属性来指定引导类

  


​    







---

 

# Summary

主要学习了MybatisPlus的内容，尝试在Springboot的框架下进行整合。但是在配置整合的时候碰到了一些问题，一直没有办法配置成功。似乎是Mybatis和MybatisPlus之间的版本没有整合，无法正常兼容，导致一直没有办法正常使用MybatisPlus。
