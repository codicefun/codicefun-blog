package com.codicefun.blog.dao;

import com.codicefun.blog.model.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    /**
     * 查询发布的博客
     *
     * @param pageable 分页接口
     * @return 可分页的博客集合
     */
    Page<Blog> findByPublishedTrue(Pageable pageable);

    /**
     * 查询推荐和发布的博客
     *
     * @param pageable 分页接口
     * @return 博客列表
     */
    List<Blog> findByRecommendTrueAndPublishedTrue(Pageable pageable);

    /**
     * 根据博客的 type id 查询
     *
     * @param pageable 分页接口
     * @param typeId   博客的 type id
     * @return 可分页的博客集合
     */
    Page<Blog> findByTypeId(Long typeId, Pageable pageable);

    /**
     * 根据博客标题和内容查询
     *
     * @param query    查询字符串
     * @param pageable 分页接口
     * @return 可分页的博客集合
     */
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);

    /**
     * 分组查询博客的年份（降序）
     *
     * @return 年份列表
     */
    @Query("select function('date_format', b.createTime, '%Y') as year from Blog b where b.published = true group by function('date_format', b.createTime, '%Y')")
    List<String> findByGroupYear();

    /**
     * 按年份分组查询博客（降序）
     *
     * @param year 年分
     * @return 博客列表
     */
    @Query("select b from Blog b where function('date_format', b.createTime, '%Y') = ?1 and b.published = true order by b.createTime desc")
    List<Blog> findByYear(String year);

    /**
     * 更新博客的浏览数
     *
     * @param id 博客 id
     */
    @Transactional
    @Modifying
    @Query("update Blog b set b.views = b.views + 1 where b.id = ?1")
    void updateViews(Long id);

    /**
     * 计算发布的博客数发布，true or false
     *
     * @return 发布的博客数
     */
    Long countByPublishedTrue();

}
