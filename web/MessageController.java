package com.blogdemo.privateblog.web;

import com.blogdemo.privateblog.po.Message;
import com.blogdemo.privateblog.po.User;
import com.blogdemo.privateblog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/message")
    public String message(){
        return "message";
    }

    @GetMapping("/messagecomment")
    public String messageComment(Model model){
        model.addAttribute("messages",messageService.listMessage());
        return "message::messageList";
    }

    @PostMapping("/message")
    public String post(Message message, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            message.setAvatar(user.getAvatar());
            message.setAdminComment(true);
        } else {
            message.setAvatar(avatar);
        }
        messageService.saveMessage(message);
        return "redirect:/messagecomment";
    }


}
