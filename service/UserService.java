package com.blogdemo.privateblog.service;

import com.blogdemo.privateblog.po.User;

public interface UserService {
    User checkUser(String username,String password);
}
