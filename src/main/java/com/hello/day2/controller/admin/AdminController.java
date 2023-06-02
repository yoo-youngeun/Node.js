package com.hello.day2.controller.admin;

import com.hello.day2.model.entity.Users;
import com.hello.day2.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/am")
public class AdminController {
    @Autowired
    private UsersService service;

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
//        mv.setViewName("/html/admin/index.html");
        mv.setViewName("/html/admin/index");
        return mv;
    }

    @GetMapping("/iframe")
    public ModelAndView iframe() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/html/admin/iframe");
        return mv;
    }

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/html/admin/home");
        return mv;
    }

    @GetMapping("/hiddenForm")
    public ModelAndView hiddenForm(Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/html/admin/hiddenForm");
//        return "/html/admin/iframe";
        return mv;
    }

    @GetMapping("/users")
    public ModelAndView getUsers() {
        ModelAndView mv = new ModelAndView();
//        mv.setViewName("/html/admin/users/searchUser.html");
        mv.setViewName("/html/admin/users/searchUser");

        return mv;
    }


    @GetMapping("/searchUsers")
    public ModelAndView searchUser(Model model) {
        ModelAndView mv = new ModelAndView();
        List<Users> usersList = service.searchUsers();
        mv.setViewName("/html/admin/users/searchUser");
        mv.addObject("userList", usersList);
        model.addAttribute("usersList", usersList);
//        return "/html/admin/users/searchUser.html";
        return mv;
    }
}
