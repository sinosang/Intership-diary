package com.mp.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mp.Controller.utils.R;
import com.mp.Pojo.User;
import com.mp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin   //后端关闭同源政策，允许跨域访问，UserController下的类，每个接口都支持跨域访问
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

//    查询类统一返回true+数据
//    内部包含数据的，需要用true来执行
    @GetMapping("/all")
    public R getAll(){
        return new R(true,userService.getAll());
    }
    @GetMapping("/id")
    public R selectByid(Integer id){

        return new R(true,userService.getById(id));
    }

//    异步提交参数，使用RequestBody的请求体方式进行参数的传递
    @PostMapping("/add")
    public R save(@RequestBody User user){
//        R r = new R();
//        boolean flag = userService.save(user);
//        r.setFlag(flag);
        return new R(userService.save(user));
    }
    @PutMapping("/update")
    public R update (@RequestBody User user){
        return new R(userService.update(user));
    }

    @DeleteMapping("/delete")
    public R delete (Integer id){
        return new R(userService.delete(id));
    }

    @GetMapping("/page/{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize){
        return new R(true,userService.getPage(currentPage,pageSize));
    }

    @GetMapping("/getName")
    public R selectByName(String name){
        return new R(true,userService.selectByName(name));
    }

//    结合分页，按照条件进行查询
    @GetMapping("/page2/{currentPage}/{pageSize}")
    public R getPage2(@PathVariable int currentPage,@PathVariable int pageSize,User user){
        IPage<User> page = userService.getPage2(currentPage,pageSize,user);
//       如果当前页面的页码大于总页码，那么重新执行查询操作，使用最大页码作为当前页码
        if(currentPage> page.getPages()){
            page= userService.getPage2((int)page.getPages(),pageSize,user);
            return new R(true,page);
        }

        return new R(true,userService.getPage2(currentPage,pageSize, user));
    }
}
