package com.hello.day2.service;

import com.hello.day2.enumclass.UserStatus;
import com.hello.day2.model.entity.Users;
import com.hello.day2.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public List<Users> searchUsers() {
        List<Users> userList = usersRepository.findAll();
        return userList;
    }

    public List<Users> searchUser(Map<String, String> param) {
        String userid = param.get("userid").trim();
        String name = param.get("name").trim();
        String status = param.get("status").trim();

        System.out.println("userid:::"+userid);
        System.out.println("name:::"+name);
        System.out.println("status:::"+status);
        List<Users> usersList = new ArrayList<>();

        if (checkNull(userid) && checkNull(name)) { // 아이디, 이름 모두 존재
            System.out.println("아이디, 이름 모두 존재");
            usersList = usersRepository.findUsersByUseridAndNameLikeAndStatus(userid, "%"+name+"%", UserStatus.valueOf(status));
        } else if (checkNull(userid) && !checkNull(name)) { // 아이디만 존재
            System.out.println("아이디만 존재");
            usersList = usersRepository.findUsersByUseridLikeAndStatus("%"+userid+"%", UserStatus.valueOf(status));

        } else if (!checkNull(userid) && checkNull(name)) { // 이름만 존재
            System.out.println("이름만 존재");
            usersList = usersRepository.findUsersByNameLikeAndStatus("%"+name+"%", UserStatus.valueOf(status));
        } else if (!checkNull(userid) && !checkNull(name) && checkNull(status)) { // 상태만 존재
            System.out.println("이름만 존재");
            usersList = usersRepository.findUsersByStatus(UserStatus.valueOf(status));
        } else {
            usersList = null;
        }

        return usersList;
    }

    public boolean checkNull(String param) {
        Boolean check = false;
        if (param != null && param != "" && param.length() != 0) { check = true; }

        return check;
    }

    public Users updateUser(Map<String, String> param) throws Exception {
        System.out.println("====================== updateUser start ======================");
        Users user = usersRepository.findUsersByUserid(param.get("userid"));
        try {
            if (user != null) {
                user.setStatus(UserStatus.valueOf(param.get("status")));
                user.setHp(param.get("hp"));
                user.setEmail(param.get("email"));

                user = usersRepository.save(user);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
