package com.hello.day2.service;

import com.hello.day2.model.entity.Users;
import com.hello.day2.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public List<Users> searchUsers() {
        List<Users> usersList = new ArrayList<>();
        usersList = usersRepository.findAll();

        return usersList;
    }
}
