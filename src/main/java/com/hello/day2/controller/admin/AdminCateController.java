package com.hello.day2.controller.admin;

import com.hello.day2.enumclass.CateType;
import com.hello.day2.model.entity.AdminUser;
import com.hello.day2.model.entity.Category;
import com.hello.day2.model.entity.Users;
import com.hello.day2.repository.AdminUserRepository;
import com.hello.day2.repository.UsersRepository;
import com.hello.day2.service.CategoryService;
import com.hello.day2.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;

@Controller
@RequestMapping("/am/cate")
public class AdminCateController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private CategoryService cateService;

    @Autowired
    private UsersRepository usersRepository;
    
    @Autowired
    private AdminUserRepository adminUsersRepository;

    @GetMapping("")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("/html/admin/cate/searchCate");
        return mv;
    }


//    @GetMapping("/searchUsers")
//    @ResponseBody
//    public List<Users> searchUsers() {
//        List<Users> usersList = usersService.searchUsers();
//        return usersList;
//    }

//    @GetMapping("/searchCates/top")
//    @ResponseBody
//    public List<Users> searchUsersTop() {
//        List<Users> usersList = usersService.searchUsersTop();
//        return usersList;
//    }

//    @PostMapping("/searchCates")
//    @ResponseBody
//    public List<Users> searchUser(@RequestParam(required = false) Map<String, String> param) {
//        List<Users> usersList = usersService.searchUser(param);
//        return usersList;
//    }

    @GetMapping("/searchUser/{id}")
    public ModelAndView viewPage(@PathVariable String id) {
        ModelAndView mv = new ModelAndView("/html/admin/cate/viewUser");
        return mv;
    }

//    @GetMapping("/cate/{id}")
//    @ResponseBody
//    public Optional<Users> getUser(@PathVariable("id") Long id) {
//        Optional<Users> user = usersRepository.findUsersById(id);
//        return user;
//    }

    @GetMapping("/updatePage/{id}")
    public ModelAndView updatePage(@PathVariable Long id){
        ModelAndView mv = new ModelAndView("/html/admin/cate/updateCate");
        return mv;
    }

//    @PostMapping("/updateUser/{id}")
//    @ResponseBody
//    public String updateUser(@PathVariable Long id, @RequestParam(required = false) Map<String, String> param) throws Exception {
//        System.out.println(param.get("userid")+param.get("email")+param.get("hp")+param.get("status"));
//        String userid = "";
//        try {
//            Users user = usersService.updateUser(param);
//
//            if(user != null) {
//                userid = user.getUserid();
//            } else {
//                userid = "";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return userid;
//    }

    @GetMapping("/getAdmin")
    @ResponseBody
    public List<AdminUser> getAdmin() throws Exception{
        List<AdminUser> admin = null;
        try {
            admin = adminUsersRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    @GetMapping("/getCateTypeList")
    @ResponseBody
    public List<CateType> getCateTypeList() {
        CateType[] cateTypes = CateType.values();
        List<CateType> cateList = new ArrayList<>();
        for (int i = 0; i < cateTypes.length; i++) {
            cateList.add(cateTypes[i]);
        }
        return cateList;
    }

    @GetMapping("/createCatePage")
    public ModelAndView createUserPage(){
        ModelAndView mv = new ModelAndView("/html/admin/cate/createCate");
        return mv;
    }

    @GetMapping("/checkUserid")
    @ResponseBody
    public String checkUserid(@RequestParam String userid) throws Exception {
        Users user = usersRepository.findUsersByUserid(userid);
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


    @PostMapping("/createCate")
    @ResponseBody
    public String createUser(@RequestParam(required = false) Map<String, String> param){
        Category createCate = cateService.createCate(param);
        return String.valueOf(createCate.getId());
    }

}
