package com.hello.day2.controller.client;

import com.hello.day2.model.entity.Users;
import com.hello.day2.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ci/Users")
public class ClientUsersController {
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/users")
    public List<Users> getUserList() {
        List<Users> userList = new ArrayList<>();

        userList = usersRepository.findAll();

        return userList;
    }


}
