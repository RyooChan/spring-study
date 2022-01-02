package com.example.restapp.service;

import com.example.restapp.dao.UserRepository;
import com.example.restapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public Map<String, String> getMessage() {
        return userRepository.getMessage();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(Integer userid) {
        return userRepository.getUserByUserid(userid);
    }

    @Override
    public User registUser(User user) {
        return userRepository.insertUser(user);
    }

    @Override
    public void modifyUser(Integer userid, User user) {
        user.setUserid(userid);
        userRepository.updateUser(user);
    }

    @Override
    public void removeUser(Integer userid) {
        userRepository.deleteUser(userid);
    }
}