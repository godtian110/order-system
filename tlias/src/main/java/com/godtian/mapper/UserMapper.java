package com.godtian.mapper;

import com.godtian.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    List<User> list();

    @Insert("INSERT into user(username,password,name,gender,phone,image,address,create_date,update_time) VALUES(#{username},#{password},#{name},#{gender},#{phone},#{image},#{address},#{createDate},#{updateTime})")
    void add(User user);
    @Update("UPDATE user set username=#{username},password=#{password},name=#{name},gender=#{gender},phone=#{phone},image=#{image},address=#{address},update_time =#{updateTime} where id=#{id}")
    void update(User user);
    @Delete("DELETE FROM user WHERE id=#{id}")
    void delete(Integer id);


    @Select("select * from user where name LIKE concat('%',#{name},'%')")
    List<User> search(String name);
    @Select("select * from user where username=#{username}&&password=#{password}")
    User login(String username, String password);

    @Select("select * FROM user WHERE id=#{id}")
    User getByid(Integer id);
}
