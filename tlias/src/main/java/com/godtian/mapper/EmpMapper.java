package com.godtian.mapper;

import com.godtian.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    @Select("select count(*)from emp")
    public Long count();
    @Select("select  * from emp limit #{start},#{pageSize}")
    public List<Emp> page(Integer start, Integer pageSize);

//    @Select("select * from emp")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    @Select("select * from emp where id=#{id}")
    public Emp getByid(Integer id);
    @Select("select * from emp where username=#{username}&&password=#{password}")
    Emp login(String username, String password);
}
