package com.blogdemo.privateblog.web;

import com.blogdemo.privateblog.service.FriendsLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FriendsShowController {

    @Autowired
    private FriendsLinkService friendsLinkService;

    @GetMapping("/friends")
    public String friends(Model model){
        model.addAttribute("friendlinks",friendsLinkService.listFriendsLink());
        return "friends";
    }
}
