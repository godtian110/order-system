package com.godtian.serivice;

import com.godtian.pojo.PageBean;
import com.godtian.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    PageBean list(Integer page, Integer pageSize);

    void add(User user);

    void update(User user);

    void delete(Integer id);

    List<User> search(String name);


    User login(String username, String password);

    User getByid(Integer id);
}
