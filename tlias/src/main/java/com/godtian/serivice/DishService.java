package com.godtian.serivice;

import com.godtian.pojo.Dish;
import com.godtian.pojo.PageBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DishService {
    PageBean list(Integer page, Integer pageSize,String name);

    void add(Dish dish);

    void update(Dish dish);

    void deleteDish(Integer id);

    List<Dish> getAll();

    Dish getByid(Integer id);


    List<Dish> search(String searchName, String type);
}
