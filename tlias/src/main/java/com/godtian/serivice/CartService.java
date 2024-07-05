package com.godtian.serivice;

import com.godtian.pojo.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    List<Cart> list();

    void add(Cart cart);

    void update(Cart cart);

    void delete(Integer id);

    List<Cart> search(String name);
}
