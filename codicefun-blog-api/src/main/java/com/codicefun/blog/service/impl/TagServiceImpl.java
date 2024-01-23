package com.codicefun.blog.service.impl;

import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import com.codicefun.blog.entity.po.Tag;
import com.codicefun.blog.entity.vo.PaginationVO;
import com.codicefun.blog.exception.BusinessException;
import com.codicefun.blog.mapper.TagMapper;
import com.codicefun.blog.service.TagService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagMapper tagMapper;

    public TagServiceImpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Transactional
    @Override
    public boolean add(Tag type) {
        return tagMapper.insert(type) == 1;
    }

    @Override
    public Tag getById(Integer id) {
        return tagMapper.selectById(id)
                        .orElseThrow(() -> new BusinessException(ResponseStatusEnum.RESOURCE_NOT_FOUND));
    }

    @Override
    public PaginationVO<Tag> getByEquals(Integer current, Integer size, Tag type) {
        Page<Tag> page = PageHelper.startPage(current, size);
        List<Tag> typeList = tagMapper.selectByEquals(type);

        return new PaginationVO<>(page.getTotal(), current, size, typeList);
    }

    @Transactional
    @Override
    public boolean updateById(Integer id, Tag type) {
        type.setId(id);

        return tagMapper.updateById(type) == 1;
    }

    @Transactional
    @Override
    public boolean removeById(Integer id) {
        return tagMapper.deleteById(id) == 1;
    }

}
