package com.hello.day2.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum UserStatus {
    REGISTERED(0, "등록", "사용자 가입상태"),
    UNREGISTERED(1, "탈퇴", "사용자 탈퇴상태");

    private Integer id;
    private String title;
    private String description;

}
