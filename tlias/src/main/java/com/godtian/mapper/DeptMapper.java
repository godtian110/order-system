package com.godtian.mapper;

import com.godtian.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
//  查询全部的部门信息
    @Select("select * from dept")
    List<Dept> list();
    @Delete("delete from dept where id=#{id}")
    void DeleteDept(Integer id);
    @Insert("insert into dept (name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    void add(Dept dept);

    @Select("select * from dept where id =#{id}")
    Dept getByid(Integer id);

    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void updateDept(Dept dept);
}
