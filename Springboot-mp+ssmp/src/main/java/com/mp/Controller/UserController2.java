//package com.mp.Controller;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.mp.Entity.User;
//import com.mp.Service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
////@RequestMapping("/users")
//public class UserController2 {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/all")
//    public List<User> getAll(){
//        return userService.getAll();
//    }
//    @GetMapping("/id")
//    public User selectByid(Integer id){
//        return userService.getById(id);
//    }
//
////    异步提交参数，使用RequestBody的请求体方式进行参数的传递
//    @PostMapping("/add")
//    public Boolean save(@RequestBody User user){
//        return userService.save(user);
//    }
//    @PutMapping("/update")
//    public Boolean update (@RequestBody User user){
//        return userService.update(user);
//    }
//
//    @DeleteMapping("/delete")
//    public Boolean delete (Integer id){
//        return userService.delete(id);
//    }
//
//    @GetMapping("/page/{currentPage}/{pageSize}")
//    public IPage<User> getPage(@PathVariable int currentPage,@PathVariable int pageSize){
//        return userService.getPage(currentPage,pageSize);
//    }
//
//
//}
