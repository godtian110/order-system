package com.godtian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Short gender;
    private String phone;
    private String image;
    private String address;
    private LocalDateTime createDate;
    private LocalDateTime updateTime;
}
