package com.hello.day2.repository;

import com.hello.day2.Day2ApplicationTests;
import com.hello.day2.enumclass.UserStatus;
import com.hello.day2.model.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryTest extends Day2ApplicationTests {
    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void create() {
        Users user = Users.builder()
                .userid("banana")
                .userpw("2222")
                .name("반하나")
                .hp("010-2222-2222")
                .email("banana@banana.com")
                .status(UserStatus.REGISTERED)
                .regDate(LocalDateTime.now())
                .build();

        Users newUser = usersRepository.save(user);
    }

    @Test
    public void update() {
        Optional<Users> user = usersRepository.findUsersById(1L);

        user.ifPresent(
                selectUser -> {
                    selectUser.setEmail("mellong@mellon.com");
                    selectUser.setStatus(UserStatus.UNREGISTERED);

                    usersRepository.save(selectUser);
                }
        );
    }

    @Test
    public void readAll() {
        List<Users> user = usersRepository.findAll();

        for(Users u : user) {
            System.out.println("name : " + u.getName());
            System.out.println("email : " + u.getEmail());
            System.out.println("status : " + u.getStatus());
            System.out.println("===============================");
        }
    }

    @Test
    public void read() {
        Optional<Users> user = usersRepository.findUsersById(3L);

//        UserStatusConverter converter =

        user.ifPresent(
                selectUser -> {
                    System.out.println("name : " + selectUser.getName());
                    System.out.println("email : " + selectUser.getEmail());
                    System.out.println("status : " + selectUser.getStatus());
                }
        );
    }
}
