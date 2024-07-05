package com.godtian.mapper;

import com.godtian.pojo.Car;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CarMapper {

    @Insert("INSERT into car(dish_id,user_id,num,time) " +
            "VALUES(#{dishId},#{userId},#{num},#{time})")
    void add(Car car);

    List<Car> list(Integer id);

    @Update("update car set num=#{num} WHERE id = #{id}")
    void update(Car car);
    @Delete("DELETE FROM car where id=#{id}")
    void del(Integer id);
}
