package com.godtian.controller;

import com.godtian.pojo.Orders;
import com.godtian.pojo.OrdersDishes;
import com.godtian.pojo.Result;
import com.godtian.serivice.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @PostMapping
    public Result save(@RequestBody Orders orders){
        log.info("用户下单：{}",orders.getUserId());
        ordersService.save(orders);
        return Result.success();
    }

    @GetMapping
    public Result GetAll(){
        log.info("查询所有订单");
        List<Orders> ordersList = ordersService.getAll();
        return Result.success(ordersList);
    }

    @GetMapping("/{userId}")
    public Result save(@PathVariable Integer userId){
        log.info("根据用户id查询订单");
        List<Orders> ordersList = ordersService.getById(userId);
        return Result.success(ordersList);
    }
    @DeleteMapping ("/{id}")
    public Result del(@PathVariable Integer id){
        log.info("根据id删除订单");
        ordersService.delById(id);
        return Result.success();
    }
    @GetMapping ("/update")
    public Result chu(Integer id,String state){
        log.info("根据id修改订单状态");
        ordersService.updateById(id,state);
        return Result.success();
    }

    @GetMapping("/detail/{ordersId}")
    private Result detail(@PathVariable Integer ordersId){
        log.info("根据订单id查询订单内容");
        List<OrdersDishes> ordersDishesList=ordersService.getDetail(ordersId);
        return  Result.success(ordersDishesList);
    }

}
