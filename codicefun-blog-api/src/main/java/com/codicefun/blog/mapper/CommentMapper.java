package com.codicefun.blog.mapper;

import com.codicefun.blog.entity.dto.CommentDto;
import com.codicefun.blog.entity.po.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    @Mapping(target = "articleId", source = "article.id")
    @Mapping(target = "parentId", source = "parent.id")
    CommentDto po2dto(Comment comment);

    @Mapping(target = "article.id", source = "articleId")
    @Mapping(target = "parent.id", source = "parentId")
    Comment dto2po(CommentDto commentDto);

}
