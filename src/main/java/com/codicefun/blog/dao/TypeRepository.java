package com.codicefun.blog.dao;

import com.codicefun.blog.entity.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Long> {

    /**
     * 根据分类名查找分类
     *
     * @param name 分类名
     * @return 查找结果
     */
    Type findByName(String name);

    /**
     * 根据条件查询指定的分类数
     *
     * @param pageable 分页接口
     * @return 分类列表
     */
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);

}
