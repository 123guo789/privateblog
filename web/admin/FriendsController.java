package com.blogdemo.privateblog.web.admin;

import com.blogdemo.privateblog.po.FriendsLink;
import com.blogdemo.privateblog.service.FriendsLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/admin")
public class FriendsController {

    @Autowired
    private FriendsLinkService friendsLinkService;

    @GetMapping("/friendlinks")
    public String friend(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                                 Pageable pageable, Model model){
        model.addAttribute("page",friendsLinkService.listFriendsLink(pageable));
        return "admin/friendlinks";
    }

    @GetMapping("/friendlinks/input")
    public String input(Model model){
        model.addAttribute("friendlink",new FriendsLink());
        return "admin/friendlinks-input";
    }

    @GetMapping("/friendlinks/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("friendlink",friendsLinkService.getFriendsLink(id));
        return "admin/friendlinks-input";
    }

    @PostMapping("/friendlinks")
    public String post(@Validated FriendsLink friendsLink, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()){
            return "admin/friendlinks-input";
        }
        friendsLink.setCreateTime(new Date());
        FriendsLink f=friendsLinkService.saveFriendsLink(friendsLink);
        if (f==null){
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/friendlinks";
    }

    @PostMapping("/friendlinks/{id}")
    public String editpost(@Validated FriendsLink friendsLink, BindingResult result, @PathVariable Long id, RedirectAttributes attributes){
        friendsLink.setCreateTime(new Date());
        FriendsLink f=friendsLinkService.updateFriendsLink(id,friendsLink);
        if (f==null){
            attributes.addFlashAttribute("message","编辑失败");
        }else{
            attributes.addFlashAttribute("message","编辑成功");
        }
        return "redirect:/admin/friendlinks";
    }

    @GetMapping("/friendlinks/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        friendsLinkService.deleteFriendsLink(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/friendlinks";
    }

}
