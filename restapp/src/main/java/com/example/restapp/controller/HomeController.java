package com.example.restapp.controller;

import com.example.restapp.service.SecurityService;
import com.example.restapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController     // restful api의 home~
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("")
    public Map<String, String> home() {

        Map<String, String> res = this.userService.getMessage();

        return res;
    }

    @GetMapping("security/generate/token")      // 토큰을 발행하는 endpoint
    public Map<String, Object> generateToken(@RequestParam String subject) {
        String token = securityService.createToken(subject, 1000 * 60 * 60 * 24L);
        Map<String, Object> map = new HashMap<>();
        map.put("userid", subject);
        map.put("token", token);
        return map;
    }

    @GetMapping("security/get/subject")     // 토큰을 요청할 때 토큰에서 subject를 발행해서 넘겨주는 endpoint
    public String getSubject(@RequestParam String token) {
        String subject = securityService.getSubject(token);
        return subject;
    }

}
