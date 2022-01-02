package com.example.restapp.service;

import com.example.restapp.dao.UserRepository;
import com.example.restapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    List<User> getUsers();

    Map<String, String> getMessage();
    User getUserById(Integer userid);
    User registUser(User user);
    void modifyUser(Integer userid, User user);
    void removeUser(Integer userid);
}
