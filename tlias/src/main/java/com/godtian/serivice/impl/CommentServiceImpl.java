package com.godtian.serivice.impl;

import com.godtian.mapper.CommentMapper;
import com.godtian.pojo.Comment;
import com.godtian.serivice.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<Comment> getComment(Integer dishId) {
        return commentMapper.getComment(dishId);
    }
}
