package com.codicefun.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codicefun.blog.entity.po.Type;
import com.codicefun.blog.mapper.TypeMapper;
import com.codicefun.blog.service.TypeService;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Override
    public IPage<Type> listByTerms(IPage<Type> page, Type type) {
        LambdaQueryWrapper<Type> queryWrapper = new LambdaQueryWrapper<>();

        if (type != null) {
            // Add terms
        }

        return page(page, queryWrapper);
    }
}
