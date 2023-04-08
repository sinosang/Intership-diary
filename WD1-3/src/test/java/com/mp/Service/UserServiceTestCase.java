package com.mp.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTestCase {
    @Autowired
    private UserService userService;

    @Test
    void testGetById(){
        System.out.println(userService.getById(2));
    }

    @Test
    void testGetAll(){
        System.out.println(userService.getAll());
    }

}
