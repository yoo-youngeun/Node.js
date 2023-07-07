package com.hello.day2.model.entity;

import com.hello.day2.enumclass.CateType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CateType type;
    private String title;
    private LocalDateTime regDate;
    private String updateBy;
    private LocalDateTime updateDate;
}
