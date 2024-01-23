package com.codicefun.blog.service.impl;

import com.codicefun.blog.entity.enums.ResponseStatusEnum;
import com.codicefun.blog.entity.po.Type;
import com.codicefun.blog.entity.vo.PaginationVO;
import com.codicefun.blog.exception.BusinessException;
import com.codicefun.blog.mapper.TypeMapper;
import com.codicefun.blog.service.TypeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    private final TypeMapper typeMapper;

    public TypeServiceImpl(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    @Transactional
    @Override
    public boolean add(Type type) {
        return typeMapper.insert(type) == 1;
    }

    @Override
    public Type getById(Integer id) {
        return typeMapper.selectById(id)
                         .orElseThrow(() -> new BusinessException(ResponseStatusEnum.RESOURCE_NOT_FOUND));
    }

    @Override
    public PaginationVO<Type> getByEquals(Integer current, Integer size, Type type) {
        Page<Type> page = PageHelper.startPage(current, size);
        List<Type> typeList = typeMapper.selectByEquals(type);

        return new PaginationVO<>(page.getTotal(), current, size, typeList);
    }

    @Transactional
    @Override
    public boolean updateById(Integer id, Type type) {
        type.setId(id);

        return typeMapper.updateById(type) == 1;
    }

    @Transactional
    @Override
    public boolean removeById(Integer id) {
        return typeMapper.deleteById(id) == 1;
    }

}
