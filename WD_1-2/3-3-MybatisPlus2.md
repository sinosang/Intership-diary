# 3-3

- 上午

  配置好了Springboot+Mybatis-plus的环境

  把昨天学习的MybatisPlus相关的技术知识进行代码实现
  
  - 条件查询
  
    ```
    @Test 
    void testGetByCondition(){
      IPage page = new Page(1,10);
      LambdaQueryWrapper < Book > lqw = new LambdaQueryWrapper < Book > ()；
      lqw.like(Book::getName,"Spring");
      bookDao.selectPage(page,lqw);
    }
    
    
    @Test
    void testGetByCondition(){
      QueryWrapper < Book > qw = new QueryWrapper < Book > ();
      qw.like("name","Spring");
      bookDao.selectList(qw);
    
    }
    
    ```
  
    



- 下午

  SSMP整合

  | 类别                   | 功能                                                         |
  | ---------------------- | ------------------------------------------------------------ |
  | 实体类开发             | get/set                                                      |
  | Dao/Mapper层(数据层)   | 整合MybatisPlus，制作数据层的测试类                          |
  | Service开发(业务层)    | 基于MybatisPlus进行增量开发，制作业务层测试类                |
  | Controller开发(表现层) | 基于Restful开发，使用PostMan进行接口测试，制作前后端开发协议 |
  | 页面开发               | 前端开发                                                     |
  | 按照条件查询           | 页面功能调整，Controller修正功能，Service修正功能            |

  

  