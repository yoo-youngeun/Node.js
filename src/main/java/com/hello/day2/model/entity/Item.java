package com.hello.day2.model.entity;

import com.hello.day2.enumclass.ItemStatus;
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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ItemStatus status;
    private String title;
    private String content;
    private Long price;
    private LocalDateTime regDate;
    private String createBy;
    private LocalDateTime updateDate;
    private String updateBy;
    private Long partnerId;
}
