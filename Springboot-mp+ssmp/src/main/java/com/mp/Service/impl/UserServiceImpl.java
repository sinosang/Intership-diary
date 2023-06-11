//package com.mp.Service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.mp.Service.UserService;
//import com.mp.Mapper.UserMapper;
//import com.mp.Entity.User;
//import org.apache.logging.log4j.util.Strings;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Override
//    public Boolean save(User user){
////        当影响的行数>0，则证明操作成功
//        return userMapper.insert(user) >0 ;
//    }
//
//    @Override
//    public Boolean update(User user) {
//        return userMapper.updateById(user) > 0 ;
//    }
//
//    @Override
//    public Boolean delete(Integer id) {
//        return userMapper.deleteById(id) > 0 ;
//    }
//
//    @Override
//    public User getById(Integer id) {
//        return userMapper.selectById(id);
//    }
//
//
//    @Override
//    public List<User> getAll() {
//        return userMapper.selectList(null);
//    }
//
//    @Override
//    public User selectByName(String name) {
//        return userMapper.getNameHand(name);
//    }
//
//    @Override
//    public IPage<User> getPage(int currentPage, int pageSize) {
//        IPage page = new Page(currentPage,pageSize);
//        userMapper.selectPage(page,null);
//        return page;
//    }
//
//
//    @Override
//    public IPage<User> getPage2(int currentPage, int pageSize, User user) {
//        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
//        lqw.like(Strings.isNotEmpty(user.getName()),User::getId,user.getName());
//        IPage page = new Page(currentPage,pageSize);
//        userMapper.selectPage(page,null);
//        return page;
//    }
//
//
//}
