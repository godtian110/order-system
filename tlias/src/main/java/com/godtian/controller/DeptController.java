package com.godtian.controller;

import com.godtian.pojo.Dept;
import com.godtian.pojo.Result;
import com.godtian.serivice.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list(){
        log.info("查询所有部门数据");
        List<Dept> deptList= deptService.list();
        return Result.success(deptList);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据ID删除部门");
        deptService.deleteDept(id);
        return Result.success();
    }
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门:{}",dept.getName());
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getByid(@PathVariable Integer id){
        log.info("查询部门信息：{}", id);
        Dept dept=deptService.getByid(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result updateDept(@RequestBody Dept dept){
        log.info("修改部门信息:{},{}",dept.getId(),dept.getName());
        deptService.updateDept(dept);
        return Result.success();
    }
}
