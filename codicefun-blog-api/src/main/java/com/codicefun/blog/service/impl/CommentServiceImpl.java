package com.codicefun.blog.service.impl;

import com.codicefun.blog.entity.dao.CommentDao;
import com.codicefun.blog.entity.dto.CommentDto;
import com.codicefun.blog.entity.dto.PageDto;
import com.codicefun.blog.entity.po.Comment;
import com.codicefun.blog.mapper.CommentMapper;
import com.codicefun.blog.service.CommentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentDao commentDao, CommentMapper commentMapper) {
        this.commentDao = commentDao;
        this.commentMapper = commentMapper;
    }

    @Transactional
    @Override
    public boolean add(CommentDto commentDto) {
        Comment comment = commentMapper.dto2po(commentDto);

        return commentDao.insert(comment) == 1;
    }

    @Override
    public PageDto<CommentDto> getAll(Integer current, Integer size) {
        Page<Object> page = PageHelper.startPage(current, size);
        List<Comment> commentList = commentDao.selectAll();
        List<CommentDto> commentDtoList = commentMapper.poList2dtoList(commentList);

        return PageDto.of(page.getTotal(), current, size, commentDtoList);
    }

    @Override
    public PageDto<CommentDto> getByArticleId(Integer articleId, Integer current, Integer size) {
        Page<Comment> page = PageHelper.startPage(current, size);
        List<Comment> commentList = commentDao.selectByArticleId(articleId);
        List<CommentDto> commentDtoList = commentMapper.poList2dtoList(commentList);

        return PageDto.of(page.getTotal(), current, size, commentDtoList);
    }

    @Transactional
    @Override
    public boolean updateById(Integer id, CommentDto commentDto) {
        commentDto.setId(id);
        Comment comment = commentMapper.dto2po(commentDto);

        return commentDao.updateById(comment) == 1;
    }

    @Transactional
    @Override
    public boolean removeById(Integer id) {
        return commentDao.deleteById(id) == 1;
    }

}
