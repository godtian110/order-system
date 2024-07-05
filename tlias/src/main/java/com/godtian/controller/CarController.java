package com.godtian.controller;

import com.godtian.pojo.Car;
import com.godtian.pojo.Result;
import com.godtian.serivice.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping
    public Result add(@RequestBody Car car){
        log.info("申请加入购物车");
        carService.add(car);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Car car){
        log.info("申请加入购物车");
        carService.update(car);
        return Result.success();
    }

    //根据用户id查询购物车中的东西
    @GetMapping("/{id}")
    public Result list(@PathVariable Integer id){
        log.info("根据用户id查询用户购物车");
        List<Car> carList = carService.list(id);
        return Result.success(carList);
    }
}
