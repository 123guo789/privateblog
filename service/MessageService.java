package com.blogdemo.privateblog.service;

import com.blogdemo.privateblog.po.Message;

import java.util.List;

public interface MessageService {
    List<Message> listMessage();
    Message saveMessage(Message message);
}
