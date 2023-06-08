package com.hello.day2.service;

import com.hello.day2.model.entity.Users;
import com.hello.day2.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public List<Users> searchUsers() {
        List<Users> userList = usersRepository.findAll();
        return userList;
    }

    public List<Users> searchUser(Map<String, String> param) {
        String userid = param.get("userid");
        String name = param.get("name");
        System.out.println("userid ::: " + userid);
        System.out.println("name ::: " + name);
        List<Users> usersList = new ArrayList<>();

        if (checkNull(userid) && checkNull(name)) {
            usersList = usersRepository.findUsersByUseridAndName(userid, name);
        } else if (checkNull(userid) && !checkNull(name)) {
            Users findUser = usersRepository.findUsersByUserid(userid);
            usersList.add(findUser);
        } else if (!checkNull(userid) && checkNull(name)) {
            usersList = usersRepository.findUsersByNameLike("%"+name+"%");
        } else {
            usersList = null;
        }

        return usersList;
    }

    public boolean checkNull(String param) {
        Boolean check = false;
        if (param != null && !(param.equals(""))) { check = true; }

        return check;
    }
}
