package com.hello.day2.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
    남자(0, "man"),
    여자(1, "woman");

    private Integer id;
    private String title;
}
