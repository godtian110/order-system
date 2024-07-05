package com.godtian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.transform.sax.SAXResult;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private Integer id;
    private Integer dishId;
    private Integer userId;
    private Integer num;
    private LocalDateTime time;

    private String dishName;
    private double price;
    private String img;
    private String username;
    private String name;
}
