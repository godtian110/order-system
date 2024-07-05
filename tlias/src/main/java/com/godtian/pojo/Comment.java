package com.godtian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer id;
    private Integer dishId;
    private Integer userId;
    private String status;
    private String  context;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
