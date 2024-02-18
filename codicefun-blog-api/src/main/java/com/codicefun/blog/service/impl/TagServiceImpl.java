package com.codicefun.blog.service.impl;

import com.codicefun.blog.entity.dao.TagDao;
import com.codicefun.blog.entity.dto.PageDto;
import com.codicefun.blog.entity.po.Tag;
import com.codicefun.blog.exception.BusinessException;
import com.codicefun.blog.service.TagService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;

    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Transactional
    @Override
    public boolean add(Tag type) {
        return tagDao.insert(type) == 1;
    }

    @Override
    public Tag getById(Integer id) {
        return tagDao.selectById(id)
                     .orElseThrow(BusinessException::notFoundResource);
    }

    @Override
    public PageDto<Tag> getByEquals(Integer current, Integer size, Tag type) {
        // TODO: 'Page<Tag>' used without 'try'-with-resources statement
        Page<Tag> page = PageHelper.startPage(current, size);
        List<Tag> typeList = tagDao.selectByEquals(type);

        // TODO: typeList is equal page
        return PageDto.of(page.getTotal(), current, size, typeList);
    }

    @Override
    public List<Tag> getAll() {
        return tagDao.selectAll();
    }

    @Transactional
    @Override
    public boolean updateById(Integer id, Tag type) {
        type.setId(id);

        return tagDao.updateById(type) == 1;
    }

    @Transactional
    @Override
    public boolean removeById(Integer id) {
        return tagDao.deleteById(id) == 1;
    }

}
