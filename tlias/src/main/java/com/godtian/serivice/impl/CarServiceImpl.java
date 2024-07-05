package com.godtian.serivice.impl;

import com.godtian.mapper.CarMapper;
import com.godtian.pojo.Car;
import com.godtian.serivice.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;

    @Override
    public void add(Car car) {
        car.setTime(LocalDateTime.now());
        carMapper.add(car);
    }

    @Override
    public List<Car> list(Integer id) {
        return carMapper.list(id);
    }

    @Override
    public void update(Car car) {
        car.setTime(LocalDateTime.now());
        carMapper.update(car);
    }
}
