package com.godtian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDishes {
    private Integer id;
    private Integer orderId;
    private Integer dishId;
    private String dishName;
    private double price;
    private Integer num;
}
