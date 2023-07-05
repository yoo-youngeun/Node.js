package com.hello.day2.model.entity;

import com.hello.day2.enumclass.Gender;
import com.hello.day2.enumclass.UserStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userid;
    private String userpw;
    private String name;
    private String hp;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
//    @Convert(converter = UserStatusConverter.class)
    @Enumerated(EnumType.STRING)
    private UserStatus status; // REGISTERED, UNREGISTERED
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

}
