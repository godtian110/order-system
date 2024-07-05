package com.godtian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    private Integer id;
    private String name;
    private double price;
    private Integer status;//状态
    private Integer type;//类型
    private String img;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
