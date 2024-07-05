package com.godtian.serivice.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.godtian.mapper.UserMapper;
import com.godtian.pojo.Emp;
import com.godtian.pojo.PageBean;
import com.godtian.pojo.User;
import com.godtian.serivice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageBean list(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<User> userList=userMapper.list();
        Page<User> p= (Page<User>) userList;
        return new PageBean(p.getTotal(),p.getResult());
    }

    @Override
    public void add(User user) {
        user.setCreateDate(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.add(user);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }

    @Override
    public List<User> search(String name) {
        return userMapper.search(name);
    }

    @Override
    public User login(String username, String password) {
        return userMapper.login(username,password);
    }

    @Override
    public User getByid(Integer id) {
        return userMapper.getByid(id);
    }


}
