package com.godtian.mapper;

import com.godtian.pojo.OrdersDishes;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersDishesMapper {
    @Insert("INSERT into order_dishes(order_id,dish_id,num) VALUES(#{orderId},#{dishId},#{num})")
    void save(OrdersDishes ordersDishes);
}
