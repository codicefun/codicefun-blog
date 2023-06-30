package com.codicefun.blog.dao;

import com.codicefun.blog.entity.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    /**
     * 根据标签名查找标签
     *
     * @param name 标签名
     * @return 查找结果
     */
    Tag findByName(String name);

    /**
     * 根据条件查询指定的标签数
     *
     * @param pageable 分页接口
     * @return 标签列表
     */
    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);

}
