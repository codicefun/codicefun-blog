package com.codicefun.blog.service;

import com.codicefun.blog.entity.dto.CommentDto;
import com.codicefun.blog.entity.dto.PageDto;

public interface CommentService {

    boolean add(CommentDto commentDto);

    PageDto<CommentDto> getByArticleId(Integer articleId, Integer current, Integer size);

}
