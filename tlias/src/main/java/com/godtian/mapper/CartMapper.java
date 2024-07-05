package com.godtian.mapper;

import com.godtian.pojo.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public  interface  CartMapper {
    @Select("SELECT * from dish_type")
    public List<Cart> list();
    @Insert("INSERT into dish_type (type_name,create_time,update_time)  VALUES(#{typeName},#{createTime},#{updateTime})")
    void add(Cart cart);

    @Update("update dish_type set type_name=#{typeName} WHERE id=#{id}")
    void update(Cart cart);
    @Delete("DELETE FROM dish_type WHERE id=#{id}")
    void delete(Integer id);
    @Select("select * from dish_type where type_name LIKE concat('%',#{name},'%')")
    List<Cart> search(String name);
}
