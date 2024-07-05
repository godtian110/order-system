package com.godtian.serivice.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.godtian.mapper.DishMapper;
import com.godtian.pojo.Dish;
import com.godtian.pojo.PageBean;

import com.godtian.serivice.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;

    @Override
    public PageBean list(Integer page, Integer pageSize,String name) {
        PageHelper.startPage(page,pageSize);
        List<Dish> dishList=dishMapper.list(name);
        Page<Dish> p= (Page<Dish>) dishList;
        return new PageBean(p.getTotal(),p.getResult());
    }



    @Override
    public void add(Dish dish) {
        dish.setCreateTime(LocalDateTime.now());
        dish.setUpdateTime(LocalDateTime.now());
        dishMapper.add(dish);
    }

    @Override
    public void update(Dish dish) {
        dish.setUpdateTime(LocalDateTime.now());
        dishMapper.update(dish);
    }

    @Override
    public void deleteDish(Integer id) {
        dishMapper.deleteDish(id);
    }

    @Override
    public List<Dish> getAll() {
        return dishMapper.getAll();
    }

    @Override
    public Dish getByid(Integer id) {
        return dishMapper.getByid(id);
    }

    @Override
    public List<Dish> search(String searchName, String type) {
        return dishMapper.search(searchName,type);
    }
}
