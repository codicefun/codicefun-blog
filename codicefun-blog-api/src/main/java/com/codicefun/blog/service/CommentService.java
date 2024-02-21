package com.codicefun.blog.service;

import com.codicefun.blog.entity.dto.CommentDto;
import com.codicefun.blog.entity.dto.PageDto;

public interface CommentService {

    boolean add(CommentDto commentDto);

    PageDto<CommentDto> getAll(Integer current, Integer size);

    PageDto<CommentDto> getByArticleId(Integer articleId, Integer current, Integer size);

    boolean updateById(Integer id, CommentDto commentDto);

    boolean removeById(Integer id);

}
