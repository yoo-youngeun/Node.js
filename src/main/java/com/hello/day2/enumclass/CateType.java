package com.hello.day2.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CateType {
    ETC(0, "기타"),
    INSTANT(1, "인스턴트/간편식"),
    SOURCE(2, "소스류"),
    NOODLE(3, "면류"),
    FARM(4, "농산물"),
    MARINE(5, "수산물");

    private Integer id;
    private String title;
}
