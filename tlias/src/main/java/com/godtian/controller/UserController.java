package com.godtian.controller;

import com.godtian.pojo.PageBean;
import com.godtian.pojo.Result;
import com.godtian.pojo.User;
import com.godtian.serivice.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user1){
        log.info("用户登录：{}",user1.getUsername());
        User user = userService.login(user1.getUsername(),user1.getPassword());
        if(user !=null)
            return Result.success(user);
        return Result.error("没有该用户");
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer pageSize){
        log.info("分页查询所有用户信息");
        PageBean pageBean = userService.list(page, pageSize);
        return Result.success(pageBean);
    }

    @GetMapping("/{name}")
    public Result search(@PathVariable String name){
        log.info("查询单个用户信息");
        List<User> userlist = userService.search(name);
        return Result.success(userlist);
    }

    @GetMapping("/root/{id}")
    public Result getByid(@PathVariable Integer id){
        log.info("查询单个用户信息");
        User user= userService.getByid(id);
        return Result.success(user);
    }

    @PostMapping
    public Result save(@RequestBody User user){
        log.info("插入用户信息");
        userService.add(user);
        return  Result.success();
    }

    @PutMapping
    public Result update(@RequestBody User user){
        log.info("修改用户信息:{},{}",user.getId(),user.getName());
        userService.update(user);
        return  Result.success();
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据ID删除用户");
        userService.delete(id);
        return Result.success();
    }
}
