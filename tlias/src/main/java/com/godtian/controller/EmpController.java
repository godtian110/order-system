package com.godtian.controller;

import com.godtian.pojo.Emp;
import com.godtian.pojo.PageBean;
import com.godtian.pojo.Result;
import com.godtian.serivice.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.decorators.BlockingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end
                       ) {
        log.info("员工分页查询：{}，{}，{}，{}",name,gender,begin,end);
        PageBean pageBean = empService.page(page, pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp1){
        log.info("登录请求：{}",emp1.getUsername());
        Emp emp=empService.login(emp1.getUsername(), emp1.getPassword());
        if(emp !=null)
            return Result.success(emp);
        return Result.error("没有该用户");
    }

    @GetMapping("/{id}")
    public Result getByid(@PathVariable Integer id){
        log.info("查询员工的id:{}",id);
        Emp emp=empService.getByid(id);
        return Result.success(emp);
    }

}
