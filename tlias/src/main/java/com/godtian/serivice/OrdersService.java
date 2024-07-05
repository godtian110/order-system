package com.godtian.serivice;

import com.godtian.pojo.Orders;
import com.godtian.pojo.OrdersDishes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrdersService {
    void save(Orders orders);

    List<Orders> getById(Integer userId);

    void delById(Integer id);

    List<Orders> getAll();


    void updateById(Integer id, String state);

    List<OrdersDishes> getDetail(Integer ordersId);
}
