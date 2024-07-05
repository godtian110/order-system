package com.godtian.serivice.impl;

import cn.hutool.core.util.IdUtil;
import com.godtian.mapper.CarMapper;
import com.godtian.mapper.OrdersDishesMapper;
import com.godtian.mapper.OrdersMapper;
import com.godtian.pojo.Car;
import com.godtian.pojo.Orders;
import com.godtian.pojo.OrdersDishes;
import com.godtian.serivice.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrdersDishesMapper ordersDishesMapper;
    @Autowired
    private CarMapper carMapper;
    @Override
    public void save(Orders orders) {
        orders.setTime(LocalDateTime.now());
        orders.setUuid(IdUtil.fastSimpleUUID());
        ordersMapper.save(orders);
        Integer id = ordersMapper.getByUuid(orders.getUuid());
        List<Car> cars = orders.getCars();
        for(Car car:cars){
            OrdersDishes ordersDishes = new OrdersDishes();
            ordersDishes.setDishId(car.getDishId());
            ordersDishes.setNum(car.getNum());
            ordersDishes.setOrderId(id);
            ordersDishesMapper.save(ordersDishes);
            carMapper.del(car.getId());
        }
    }

    @Override
    public List<Orders> getById(Integer userId) {
        return ordersMapper.getById(userId);
    }

    @Override
    public void delById(Integer id) {
        ordersMapper.delById(id);
    }

    @Override
    public List<Orders> getAll() {
       return ordersMapper.getAll();
    }

    @Override
    public void updateById(Integer id, String state) {
        ordersMapper.updateById(id,state);
    }

    @Override
    public List<OrdersDishes> getDetail(Integer ordersId) {
        return ordersMapper.getDetail(ordersId);
    }

}
