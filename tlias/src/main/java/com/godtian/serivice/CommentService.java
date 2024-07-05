package com.godtian.serivice;

import com.godtian.pojo.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    List<Comment> getComment(Integer dishId);
}
