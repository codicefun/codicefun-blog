package com.codicefun.blog.service;

import com.codicefun.blog.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    /**
     * 保存分类
     *
     * @param type 待保存的分类对象
     * @return 保存后的分类对象
     */
    Type saveType(Type type);

    /**
     * 根据 id 获取分类
     *
     * @param id 分类 id
     * @return 分类对象
     */
    Type getType(Long id);

    /**
     * 根据分类名获取分类
     *
     * @param name 分类名
     * @return 分类对象
     */
    Type getTypeByName(String name);

    /**
     * 列出所有分类
     *
     * @param pageable 分页接口
     * @return 可分页的分类集合
     */
    Page<Type> listType(Pageable pageable);

    /**
     * 列出所有分类
     *
     * @return 分类列表
     */
    List<Type> listType();

    /**
     * 最多列出 size 个分类
     *
     * @param size 分类数
     * @return 分类列表
     */
    List<Type> listTypeTop(Integer size);

    /**
     * 更新分类
     *
     * @param id   分类 id
     * @param type 新的分类对象
     * @return 更新后的分类对象
     */
    Type updateType(Long id, Type type);

    /**
     * 删除分类
     *
     * @param id 分类 id
     */
    void deleteType(Long id);

}
