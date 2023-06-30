package com.hello.day2.controller.admin;

import com.hello.day2.enumclass.UserStatus;
import com.hello.day2.model.entity.Users;
import com.hello.day2.repository.UsersRepository;
import com.hello.day2.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

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

    @GetMapping("/updatePage/{id}")
    public ModelAndView updatePage(@PathVariable Long id){
        ModelAndView mv = new ModelAndView("/html/admin/users/updateUser");
        return mv;
    }

    @GetMapping("/updateUser/{id}")
    @ResponseBody
    public void updateUser(@PathVariable Long id, @RequestParam(required = false) Map<String, String> param) throws Exception {
//        Users exUser = repository.findUsersByUserid(param.get("userid"));
//        Users user = service.updateUser(param);

        System.out.println(param.get("userid")+param.get("email")+param.get("hp")+param.get("status"));

//        if (user != null && exUser != null) {
//            System.out.println("기존 userid ::: " + exUser.getUserid() + "변경된 userid ::: " + user.getStatus());
//            System.out.println("기존 email ::: " + exUser.getEmail() + "변경된 email ::: " + user.getEmail());
//            System.out.println("기존 status ::: " + exUser.getStatus() + "변경된 status ::: " + user.getStatus());
//        } else {
//            System.out.println("비엇서");
//        }
    }

    @GetMapping("/getStatusList")
    @ResponseBody
    public List<UserStatus>getStatusList() {
        UserStatus[] userStatuses = UserStatus.values();
        List<UserStatus> statusList = new ArrayList<>();
        for (int i = 0; i < userStatuses.length; i++) {
            statusList.add(userStatuses[i]);
        }
        return statusList;
    }
}
