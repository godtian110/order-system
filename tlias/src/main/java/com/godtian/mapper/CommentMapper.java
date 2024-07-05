package com.godtian.mapper;

import com.godtian.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("SELECT* from comment WHERE dish_id=#{dishId}")
    List<Comment> getComment(Integer dishId);
}
