package com.godtian.controller;

import com.godtian.pojo.Cart;
import com.godtian.pojo.Result;
import com.godtian.serivice.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public Result list(){
        log.info("查询所有分类信息");
        List<Cart> cartList = cartService.list();
        return Result.success(cartList);
    }
    @GetMapping("/{name}")
    public Result search(@PathVariable String name){
        log.info("查询单个分类信息");
        List<Cart> cartList = cartService.search(name);
        return Result.success(cartList);
    }
    @PostMapping
    public Result add(@RequestBody Cart cart){
        log.info("增加分类");
        cartService.add(cart);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Cart cart){
        log.info("修改餐品信息:{},{}",cart.getId(),cart.getTypeName());
        cartService.update(cart);
        return  Result.success();
    }

    @DeleteMapping("/{id}")
    public Result deleteCart(@PathVariable Integer id){
        log.info("根据ID删除分类");
        cartService.delete(id);
        return Result.success();
    }
}
