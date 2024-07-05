package com.godtian.serivice;

import com.godtian.pojo.Emp;
import com.godtian.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public interface EmpService {

    Emp login(String username, String password);

    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    Emp getByid(Integer id);
}
