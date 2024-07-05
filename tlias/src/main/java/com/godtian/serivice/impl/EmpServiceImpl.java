package com.godtian.serivice.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.godtian.mapper.EmpMapper;
import com.godtian.pojo.Emp;
import com.godtian.pojo.PageBean;
import com.godtian.serivice.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public Emp login(String username, String password) {
        return empMapper.login(username,password);
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
//        Long count =empMapper.count();
//        Integer start =(page -1)*pageSize;
//        List<Emp> empList=empMapper.page(start,pageSize);
        PageHelper.startPage(page,pageSize);
        List<Emp> empList=empMapper.list(name,gender,begin,end);
        Page<Emp> p= (Page<Emp>) empList;
        return new PageBean(p.getTotal(),p.getResult());
    }

    @Override
    public Emp getByid(Integer id) {
        Emp emp =empMapper.getByid(id);
        return emp;
    }
}
