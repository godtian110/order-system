package com.godtian.serivice;

import com.godtian.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeptService {
    List<Dept> list();
    void deleteDept(Integer id);

    void add(Dept dept);

    Dept getByid(Integer id);

    void updateDept(Dept dept);
}
