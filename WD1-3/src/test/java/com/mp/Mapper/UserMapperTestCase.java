package com.mp.Mapper;

import com.mp.Pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTestCase {

    @Autowired
    private UserMapper userMapper;

    @Test
    void handgetId(){
        System.out.println(userMapper.getIdHand(1));
    }

    @Test
    void mpgetId(){
        System.out.println(userMapper.selectById(2));
    }
}
