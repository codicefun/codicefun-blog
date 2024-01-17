package com.codicefun.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codicefun.blog.entity.po.Tag;
import com.codicefun.blog.mapper.TagMapper;
import com.codicefun.blog.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public IPage<Tag> listByTerms(IPage<Tag> page, Tag tag) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();

        if (tag != null) {
            // Add terms
        }

        return page(page, queryWrapper);
    }
}
