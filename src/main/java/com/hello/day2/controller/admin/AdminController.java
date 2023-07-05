package com.hello.day2.controller.admin;

import com.hello.day2.enumclass.Gender;
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
        return "/html/admin/hiddenForm";
    }

    @GetMapping("/user")
    public ModelAndView getUsers(ModelAndView mv) {
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

        return user;
    }

    @GetMapping("/updatePage/{id}")
    public ModelAndView updatePage(@PathVariable Long id){
        ModelAndView mv = new ModelAndView("/html/admin/users/updateUser");
        return mv;
    }

    @PostMapping("/updateUser/{id}")
    @ResponseBody
    public String updateUser(@PathVariable Long id, @RequestParam(required = false) Map<String, String> param) throws Exception {
        System.out.println(param.get("userid")+param.get("email")+param.get("hp")+param.get("status"));
        String userid = "";

        try {
            Users user = service.updateUser(param);

            if(user != null) {
                userid = user.getUserid();
            } else {
                userid = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userid;
    }

    @GetMapping("/getStatusList")
    @ResponseBody
    public List<UserStatus> getStatusList() {
        UserStatus[] userStatuses = UserStatus.values();
        List<UserStatus> statusList = new ArrayList<>();
        for (int i = 0; i < userStatuses.length; i++) {
            statusList.add(userStatuses[i]);
        }
        return statusList;
    }

    @GetMapping("/createUserPage")
    public ModelAndView createUserPage(){
        ModelAndView mv = new ModelAndView("/html/admin/users/createUser");
        return mv;
    }

    @GetMapping("/checkUserid")
    @ResponseBody
    public String checkUserid(@RequestParam String userid) throws Exception {
        Users user = repository.findUsersByUserid(userid);
        String checkUserid = "n";
        try {
            if (user == null) {
                checkUserid = "y";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkUserid;
    }

    @GetMapping("/getGenderList")
    @ResponseBody
    public List<Gender> getGenderList() {
        Gender[] genders = Gender.values();
        List<Gender> genderList = new ArrayList<>();
        for (int i = 0; i < genders.length; i++) {
            genderList.add(genders[i]);
        }
        return genderList;
    }

    @GetMapping("/createUser")
    public String createUser(@RequestParam(required = false) Map<String, String> param){
        Users createUser = service.createUser(param);

        return String.valueOf(createUser.getId());
    }

}
