package com.codicefun.blog.entity.dao;

import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.entity.po.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleDao {

    // Create
    int insert(Article article);

    // Read
    Optional<Article> selectById(Integer id);

    List<Article> selectByEquals(Article article);

    // Update
    int updateById(Article article);

    int updateViewed(Integer id);

    // Delete
    int deleteById(Integer id);

    /**
     * Delete article's tags
     *
     * @param id article id
     * @return deleted row nums
     */
    int deleteTag(Integer id);

    int insertTag(@Param("articleId") Integer articleId, @Param("tagList") List<Tag> tagList);

}
