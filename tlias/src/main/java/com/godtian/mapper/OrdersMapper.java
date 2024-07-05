package com.godtian.mapper;

import com.godtian.pojo.Orders;
import com.godtian.pojo.OrdersDishes;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrdersMapper {
    @Insert("INSERT into orders(user_id,total,address,time,uuid) " +
            "VALUES(#{userId},#{total},#{address},#{time},#{uuid})")
    void save(Orders orders);


    @Select("SELECT id from orders where uuid = #{uuid}")
    Integer getByUuid(String uuid);

    @Select("SELECT * from orders where user_id = #{userId} order by time desc")
    List<Orders> getById(Integer userId);

    @Delete("Delete from orders where id=#{id}")
    public void delById(Integer id);

    List<Orders> getAll();

    @Update("Update orders set state=#{state} where id=#{id} ")
    void updateById(Integer id, String state);

    List<OrdersDishes> getDetail(Integer ordersId);
}
