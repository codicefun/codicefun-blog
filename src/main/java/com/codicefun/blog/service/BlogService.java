package com.codicefun.blog.service;

import com.codicefun.blog.entity.Blog;
import com.codicefun.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    /**
     * 根据 id 获取博客
     *
     * @param id 博客 id
     * @return 博客对象
     */
    Blog getBlog(Long id);

    /**
     * 根据 id 获取博客内容
     *
     * @param id 博客 id
     * @return 博客对象
     */
    Blog getContent(Long id);

    /**
     * 列出发布的博客（可分页）
     *
     * @param pageable 分页接口
     * @return 可分页的博客集合
     */
    Page<Blog> listPublished(Pageable pageable);

    /**
     * 列出最新推荐的博客，限制数目
     *
     * @param size 最大博客数
     * @return 博客列表
     */
    List<Blog> listRecommended(int size);

    /**
     * 根据多个条件列出博客
     *
     * @param query    条件对象
     * @param pageable 分页接口
     * @return 可分页的博客集合
     */
    Page<Blog> listByMultiQuery(BlogQuery query, Pageable pageable);

    /**
     * 根据搜索条件列出博客
     *
     * @param query    搜索条件
     * @param pageable 分页接口
     * @return 可分页的博客集合
     */
    Page<Blog> listByQuery(String query, Pageable pageable);

    /**
     * 根据分类列出博客
     *
     * @param typeId   分类 id
     * @param pageable 分页接口
     * @return 可分页的博客集合
     */
    Page<Blog> listByType(Long typeId, Pageable pageable);

    /**
     * 根据标签列出博客
     *
     * @param tagId    标签 id
     * @param pageable 分页接口
     * @return 可分页的博客集合
     */
    Page<Blog> listByTag(Long tagId, Pageable pageable);

    /**
     * 获取博客归档
     *
     * @return 归档信息，按年份分组
     */
    Map<String, List<Blog>> getArchive();

    /**
     * 计算发布的博客数
     *
     * @return 发布的博客数
     */
    Long countPublished();

    /**
     * 保存博客
     *
     * @param blog 待保存的博客对象
     * @return 保存后的博客对象
     */
    Blog saveBlog(Blog blog);

    /**
     * 更新博客
     *
     * @param id   博客 id
     * @param blog 待更新的博客对象
     * @return 更新后的博客对象
     */
    Blog updateBlog(Long id, Blog blog);

    /**
     * 删除博客
     *
     * @param id 待删除的博客的 id
     */
    void deleteBlog(Long id);

}
