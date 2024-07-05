package com.godtian.controller;

import com.godtian.pojo.Dish;
import com.godtian.pojo.PageBean;
import com.godtian.pojo.Result;
import com.godtian.serivice.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dishs")
public class DishController {
    @Autowired
    private DishService dishService;

    @GetMapping
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer pageSize,
                        String name){
        log.info("分页查询所有餐品信息");
        PageBean pageBean = dishService.list(page, pageSize,name);
        return Result.success(pageBean);
    }


    @GetMapping("/search")
    public Result search(String searchName,String type){
        log.info("条件查询菜品：{},{}",searchName,type);
        List<Dish> dishList=dishService.search(searchName,type);
        return Result.success(dishList);
    }

    @GetMapping("/all")
    public Result getAll(){ 
        log.info("查询所有餐品信息");
        List<Dish> dishList= dishService.getAll();
        return Result.success(dishList);
    }
    @PostMapping
    public Result add(@RequestBody Dish dish){
        log.info("添加菜品");
        dishService.add(dish);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Dish dish){
        log.info("更新菜品信息：{}",dish.getName());
        dishService.update(dish);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public  Result deleteDish(@PathVariable Integer id){
        log.info("根据id删除餐品");
        dishService.deleteDish(id);
        return Result.success();
    }
    @GetMapping("/{id}")
    public  Result getByid(@PathVariable Integer id){
        log.info("根据id查询餐品");
        Dish dish=dishService.getByid(id);
        return Result.success(dish);
    }
}
