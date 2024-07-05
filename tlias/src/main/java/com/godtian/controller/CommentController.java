package com.godtian.controller;

import com.godtian.pojo.Comment;
import com.godtian.pojo.Result;
import com.godtian.serivice.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/{dishId}")
    public Result getComment(@PathVariable Integer dishId) {
        log.info("根据dish_id查询评论:{}",dishId);
        List<Comment> commentList=commentService.getComment(dishId);
        return Result.success(commentList);
    }
}
