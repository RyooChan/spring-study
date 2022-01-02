package com.example.restapp.service;

import com.example.restapp.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTests {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testRegistUser() {
        User user = new User(1000, "kkkim");
        User res = userService.registUser(user);
    }
}
