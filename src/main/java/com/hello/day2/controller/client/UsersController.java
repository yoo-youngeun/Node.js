package com.hello.day2.controller.client;

import com.hello.day2.model.entity.Users;
import com.hello.day2.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ci")
public class UsersController {
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

    @GetMapping("/users/{id}")
    public Optional<Users> getUser(@PathVariable("id") Long id) {
        Optional<Users> user = null;
        user = usersRepository.findUsersById(id);

        if(user.isPresent()) {
            System.out.println("userid : " + user.get().getUserid());
        } else{
            System.out.println("없음");
        }
        return user;
    }

}
