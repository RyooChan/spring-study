package com.example.restapp.controller;

import com.example.restapp.annotation.TokenRequired;
import com.example.restapp.model.User;
import com.example.restapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @TokenRequired
    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userid}") // userid가 변수명으로 들어간다.
    public User getUserByUserid(@PathVariable Integer userid) {    // 두 변수명이 같으면 PathVariable을 사용 가능하다. -> userid
        return userService.getUserById(userid);
    }

    @PostMapping("")
    public User registUser(@RequestBody User user) { // requestbody : POST http프로토콜의 body를 직접 받겠다. -> User모델 클래스를 바로 넣어서 그 user model클래스의 userid, username이 서로 동일하다면 자동으로 채워준다.
        System.out.println(user);
        return userService.registUser(user);
    }

    @PutMapping("/{userid}")
    public void modifyUser(
            @PathVariable Integer userid,
            @RequestBody User user) {
        userService.modifyUser(userid, user);
    }

    @DeleteMapping("/{userid}")
    public void removeUser(@PathVariable Integer userid) {
        userService.removeUser(userid);
    }
}
