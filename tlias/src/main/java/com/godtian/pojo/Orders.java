package com.godtian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private Integer id;
    private Integer userId;
    private String name;
    private double total;
    private String state;
    private String address;
    private LocalDateTime time;
    private LocalDateTime payTime;
    private String uuid;
    private List<Car> cars;
}
