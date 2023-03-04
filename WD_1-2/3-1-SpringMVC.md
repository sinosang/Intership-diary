# 3-1

- 上午

- SpringMVC

  - 请求与映射（@RequestMapping）

    设置当前控制器的请求访问路径

    ```
    @Controller
    @RequestMapping("/user")           //配置主访问路径(访问路径前缀)
    public class UserController{
      @RequestMapping("/save")                //配置从访问路径
      @ResponseBody                           //设置操作返回的类型
      public String save(){
        System.out.println("user save data");
        return "{'model':'user save'}";       //请求响应的结果
      }
    }
    ```
    
  - 中文乱码处理

    ```
    @Override
    protected Filter[] getServletFilters(){
      CharacterEncodingFilter filter = new CharacterEncodingFilter();
      filter.setEncoding("UTF-9");
      return new Filter[](filter);
    }
    ```



---



- 几种传参类型方式     ~~白学警告~~

  - 地址参数名与形参变量名相同

    ```
    @RequestMapping("/url")
    @ResponseBody
    public String common(String name,int age){
        System.out.println("user name" + name);
        return "{'model':'user save'}"; 
    }
    ```

  - 普通参数传参，地址参数名与形参变量变量名不相同

    使用**@RequestParam**,用形参注解来绑定参数关系

    ```
    @RequestMapping("/url")
    @ResponseBody
    public String common(@RequestParam("name") String userName,int age){
        System.out.println("user name" + username);
        return "{'model':'user save'}"; 
    }
    
    request:
    get--> http://localhost/url?name=dfs&age=123
    ```

  - POJO参数，请求名参数与形参对象属性名相同，定义POJO类形参即可接收

    ```
    @RequestMapping("/url")
    @ResponseBody
    public String common(User user){
        System.out.println("user name" + user);
        return "{'model':'user save'}"; 
    }
    
    User:
    private String name;
    ```

  - 请求参数名与形参对象属性名相同，且为多个

    可以用数组或者是集合

    ```
    @RequestMapping("/url")
    @ResponseBody
    public String common(String[] hobbys){
        System.out.println("user name" + Arrays.toString(hobbys));
        return "{'model':'user save'}"; 
    }
    
    request:
    get--> http://localhost/url?hobbys=df&hobbys=sdf$hobbys=trasdf
    ```



---



- JSON传参(最常用)

  - 配置JSON数据转换的坐标

  - 设置JSON请求格式(body-raw)

  - 开启自动转换JSON的支持

    ```
    @Configuration
    @ComponentScan("com.mvc.controller")
    @EnableWebMvc   -->JSON转换
    public class SpringMvcConfig{}
    ```

  - 设置JSON数据的接收

    使用**@RequestBody**

    ```
    @RequestMapping("/url")
    @ResponseBody
    public String common(@RequestBody List<String> hobbys){
        System.out.println("user name" + hobbys;
        return "{'model':'user save'}"; 
    }
    ```

  @RequestBody和@RequestParam区别

  - @RequestParam用于接收url地址，表单传参【application/x-www-form-urlencoded】
  - @RequestBody用于接收json数据【application/json】
  - @PathVariable用于接收路径参数



---



- REST风格

  使用**@RequestMapping(value=url,method=GET/POST/PUT/DELETE)**

  | 行为             | 请求            |
  | ---------------- | --------------- |
  | 查询全部用户信息 | GET(查询)       |
  | 查询指定用户信息 | GET(查询)       |
  | 添加用户信息     | POST(新增/保存) |
  | 修改用户信息     | PUT(修改/更新)  |
  | 删除用户信息     | DELETE(删除)    |

  ```
  设置HTTP请求动作
  
  @RequestMapping(value="/url/url",method = RequestMethod.PUT)
  @ResponseBody
  public String Update(@RequestBody User user){
    System.out.println("user save "+ user);
    return "{'model':'return result'}";
  }
  
  @RequestMapping(value="/url/url/{id}",method = RequestMethod.DELETE)
  @ResponseBody
  public String Delete(@PathVariable Integer id){
    System.out.println("delete user id "+ id);
    return "{'model':'return result'}";
  }
  ```

  - 应用
  - 请求参数>1，使用JSON格式为主，用@RequestBody
  - 请求参数=1，使用@PathVariable路径变量请求
  - 发送非JSON格式，使用@RequestParam



---



- 下午

- 优化REST，便捷开发

  @RestController --> 等同于@Controller和@ResponseBody两个注解的结合

  @RequestMapping("/book") -- > 写到外，表示整个类都是以book为主访问

  @RequestMapping改写为

  @PostMapping,@GetMapping,@PutMapping,@DeleteMapping  作为方法注解

  ```
  @RestController
  @RequestMapping("/book")
  public class book controller{
  
    @PutMapping("/url")
    public String update(@RequestBody User user){
      System.out.println("user save "+ user);
      return "{'model':'return result'}";
    } 
    
    @DeleteMapping("/url")
    public String Delete(@PathVariable Integer id){
      System.out.println("delete user id "+ id);
      return "{'model':'return result'}";
    }
  }
  ```

- 学习了部分SSM框架的内容

  主要是看一下SSM框架的整合配置，比较麻烦，可能后续在Springboot中可以整合，简单的看。



---



- 拦截器

  一种动态拦截方法调用的机制

  - preHandle()在方法处理之前执行，判断是否继续向下执行

  - postHandle()方法调用之后执行

  - afterCompletion()方法在控制器的方法执行完成之后执行

    1. 声明拦截器的bean,实现HandlerInterceptor接口

       ```
       @Component
       public class ProjectInterceptor implements HandlerInterceptor{
          public boolean preHandle (...) throws Exception{
             System.out.println("preHandle");
             return true;
          }
       
          public void postHandle (...) throws Exception{
             System.out.println("postHandle");
          } 
          
          public void afterCompletion (...) throws Exception{
             System.out.println("AfterCompletion");
          } 
       
       }
       ```

    2. 定义配置类，继承WebMvcConfigurationSupport，实现addInterceptor

       ```
       @Configuration
       public class SpringMvcSupport extends WebMvcConfigurationSupport {
          @Override
          public void addInterceptors (InterceptorRegistry registry){
            ...
          }
       }
       ```

    3. 添加拦截器去，设定拦截的访问路径

       ```
       @Configuration
       public class SpringMvcSupport extends WebMvcConfigurationSupport
       {
          @Autowired
          private ProjectInterceptor project;
          
          @Override
          public void addInterceptors(InterceptorRegistry registry) {
             registry.addInterceptor(project).addPathPatterns("/url");
          }
       }
       ```

    4. 使用WebMvcConfigurer简化开发。

       ```
       @Configuration
       @ComponentScan("com.mvc.controller")
       @EnableWebMvc
       public class SpringMvcConfig implements WebMvcConfigurer {
          @Autowired
          private ProjectInterceptor project;
          
          public void addInterceptors (InterceptorRegistry registry) {
             registry.addInterceptor(project).addPathPatterns("/url","/url/*");
          }
       }
       ```

    

---

 

- Maven- 高级

  - 分模块开发思想

    ```
    Spring
      .idea
      src
         main
         |   java
         |      com.mvc
         |         controller
         |         dao
         |         domain
         |         service
         |         system.exception
         |   resources
         |   webApp
         test
         |   java
         |   resources
         target
         pom.xml
    ```

    ```
    controller
      src
      pom.xml
      ssm_controller.iml
      
    其余以此类推
    ```

  - 聚合-模块聚合

  - 继承-模块继承

  - 属性-定义与使用





# Summary

大致学习了SpringMVC的内容，主要是去了解学习了SpringMVC中的几种注解与映射，还有与之相对应的几种传参类型和传参方式，以及使用REST的风格来进行接口的编写与设计。并简单的了解了一下关于拦截器的一些设置和作用，与之相对的SSM框架整合并没有去过多的了解，Maven的一些高级操作比如聚合，继承等打算在后续的Springboot的学习中进一步的学习。

