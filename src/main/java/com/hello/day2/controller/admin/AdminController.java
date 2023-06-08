package com.hello.day2.controller.admin;

import com.hello.day2.model.entity.Users;
import com.hello.day2.repository.UsersRepository;
import com.hello.day2.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/am")
public class AdminController {
    @Autowired
    private UsersService service;

    @Autowired
    private UsersRepository repository;

    @GetMapping("")
    public ModelAndView index(ModelAndView mv) {
//        mv.setViewName("/html/admin/index.html");
        mv.setViewName("/html/admin/index");
        return mv;
    }

    @GetMapping("/iframe")
    public ModelAndView iframe(ModelAndView mv) {
        mv.setViewName("/html/admin/iframe");
        return mv;
    }

    @GetMapping("/home")
    public ModelAndView home(ModelAndView mv) {
//        ModelAndView mv = new ModelAndView();
        mv.setViewName("/html/admin/home");
        return mv;
    }

    @GetMapping("/hiddenForm")
    public String hiddenForm() {
//        mv.setViewName("/html/admin/hiddenForm");
//        return "/html/admin/iframe";
        return "/html/admin/hiddenForm";
    }

    @GetMapping("/user")
    public ModelAndView getUsers(ModelAndView mv) {
//        mv.setViewName("/html/admin/users/searchUser.html");
        mv.setViewName("/html/admin/users/searchUser");

        return mv;
    }

    @GetMapping("/searchUsers")
    @ResponseBody
    public List<Users> searchUsers() {
        List<Users> usersList = service.searchUsers();
        return usersList;
    }


    @PostMapping("/searchUser")
    @ResponseBody
    public List<Users> searchUser(@RequestParam(required = false) Map<String, String> param) {
        List<Users> usersList = service.searchUser(param);
        return usersList;
    }


    @GetMapping("/searchUser/{id}")
    public ModelAndView viewPage(@PathVariable String id) {
        ModelAndView mv = new ModelAndView("/html/admin/users/viewUser");
        return mv;
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public Optional<Users> getUser(@PathVariable("id") Long id) {
        Optional<Users> user = repository.findUsersById(id);

        if(user.isPresent()) {
            System.out.println("userid : " + user.get().getUserid());
        } else{
            System.out.println("없음");
        }
        return user;
    }

}
