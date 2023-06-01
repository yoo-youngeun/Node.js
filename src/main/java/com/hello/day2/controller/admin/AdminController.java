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
        mv.setViewName("/html/admin/index.html");
        return mv;
    }

    @GetMapping("/iframe")
    public ModelAndView iframe(Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/html/admin/iframe.html");
        mv.addObject("str", "dddddddd");
//        System.out.println("adfsfsafsdf");
//        return "/html/admin/iframe";
        return mv;
    }

    @GetMapping("/hiddenForm")
    public ModelAndView hiddenForm(Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/html/admin/hiddenForm.html");
//        return "/html/admin/iframe";
        return mv;
    }

    @GetMapping("/users")
    public ModelAndView getUsers() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/html/admin/users/searchUser.html");

        return mv;
    }


    @GetMapping("/searchUsers")
    public String searchUser(Model model) {
        ModelAndView mv = new ModelAndView();
        List<Users> usersList = service.searchUsers();
        for (int i = 0; i < usersList.size(); i++) {
            System.out.println(usersList.get(i).getId());
            System.out.println(usersList.get(i).getName());
            System.out.println(usersList.get(i).getUserid());
            System.out.println("=========");
        }

//        mv.setViewName("/html/admin/users/searchUser.html");
//        mv.addObject("usersList", usersList);
        model.addAttribute("usersList", usersList);
        return "/html/admin/users/searchUser.html";
    }
}
