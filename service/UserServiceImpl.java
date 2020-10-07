package com.blogdemo.privateblog.service;

import com.blogdemo.privateblog.dao.UserRepository;
import com.blogdemo.privateblog.po.User;
import com.blogdemo.privateblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user=userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
