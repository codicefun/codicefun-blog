package com.codicefun.blog.service.impl;

import com.codicefun.blog.entity.dao.TypeDao;
import com.codicefun.blog.entity.po.Type;
import com.codicefun.blog.entity.vo.PageVo;
import com.codicefun.blog.exception.BusinessException;
import com.codicefun.blog.service.TypeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    private final TypeDao typeDao;

    public TypeServiceImpl(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    @Transactional
    @Override
    public boolean add(Type type) {
        return typeDao.insert(type) == 1;
    }

    @Override
    public Type getById(Integer id) {
        return typeDao.selectById(id)
                      .orElseThrow(BusinessException::notFoundResource);
    }

    @Override
    public PageVo<Type> getByEquals(Integer current, Integer size, Type type) {
        Page<Type> page = PageHelper.startPage(current, size);
        List<Type> typeList = typeDao.selectByEquals(type);

        return PageVo.of(page.getTotal(), current, size, typeList);
    }

    @Override
    public List<Type> getAll() {
        return typeDao.selectAll();
    }

    @Transactional
    @Override
    public boolean updateById(Integer id, Type type) {
        type.setId(id);

        return typeDao.updateById(type) == 1;
    }

    @Transactional
    @Override
    public boolean removeById(Integer id) {
        return typeDao.deleteById(id) == 1;
    }

}
