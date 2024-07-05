package com.godtian.serivice.impl;

import com.godtian.mapper.CartMapper;
import com.godtian.pojo.Cart;
import com.godtian.serivice.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Override
    public List<Cart> list() {
        return cartMapper.list();
    }

    @Override
    public void add(Cart cart) {
        cart.setCreateTime(LocalDateTime.now());
        cart.setUpdateTime(LocalDateTime.now());
        cartMapper.add(cart);
    }

    @Override
    public void update(Cart cart) {
        cart.setUpdateTime(LocalDateTime.now());
        cartMapper.update(cart);
    }

    @Override
    public void delete(Integer id) {
        cartMapper.delete(id);
    }

    @Override
    public List<Cart> search(String name) {
        return cartMapper.search(name);
    }
}
