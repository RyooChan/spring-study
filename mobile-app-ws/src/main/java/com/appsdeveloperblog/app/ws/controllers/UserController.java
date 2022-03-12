package com.appsdeveloperblog.app.ws.controllers;


import com.appsdeveloperblog.app.ws.dto.UserInfoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping(path="/{userId}")
    public ResponseEntity<UserInfoDTO> getUser(@PathVariable String userId){
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setName(userId);
        userInfoDTO.setEmail("ryoochan@handsome.king");
        return new ResponseEntity<>(userInfoDTO, HttpStatus.OK);
    }

    @GetMapping
    public String getUsers(@RequestParam(value = "page", defaultValue = "1", required = true) int page
            , @RequestParam(value = "limit", defaultValue = "50") int limit
            , @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort ){
        return "get users was called with page = " + page + " and limit = " + limit + " and sort type is " + sort;
    }

    @PostMapping
    public String createUser(){
        return "create users was called";
    }

    @PutMapping
    public String updateUser(){
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser(){
        return "delete user was called";
    }
}