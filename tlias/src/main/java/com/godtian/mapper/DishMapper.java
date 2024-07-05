package com.godtian.mapper;

import com.godtian.pojo.Dish;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DishMapper {

    List<Dish> list(String name);

    @Insert("INSERT INTO dish(name,price,status,type,img,description,create_time,update_time)values(#{name},#{price},#{status},#{type},#{img},#{description},#{createTime},#{updateTime})")
    void add(Dish dish);
    @Update("UPDATE dish SET name=#{name},price=#{price},status=#{status},type=#{type},img=#{img},description=#{description},update_time=#{updateTime} WHERE id=#{id}")
    void update(Dish dish);
    @Delete("delete from dish where id=#{id}")
    void deleteDish(Integer id);

    @Select("SELECT * FROM dish where status = 1")
    List<Dish> getAll();
    @Select("SELECT * from dish where id=#{id}")
    Dish getByid(Integer id);

    List<Dish> search(String searchName, String type);


}
