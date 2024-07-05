package com.godtian.serivice;

import com.godtian.pojo.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    void add(Car car);

    List<Car> list(Integer id);

    void update(Car car);
}
