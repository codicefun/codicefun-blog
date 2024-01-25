package com.codicefun.blog.entity.vo;

import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.entity.po.Type;
import com.codicefun.blog.entity.po.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class ArticleVo {

    private Integer id;
    private String title;
    private String content;
    private String description;
    private String picture;
    private Integer viewed;
    private Integer liked;
    private Date createTime;
    private Date updateTime;

    private String username;
    private String typeName;

    // TODO: Remove entity convert method
    public static ArticleVo po2vo(Article article) {
        ArticleVo articleVO = new ArticleVo();
        BeanUtils.copyProperties(article, articleVO);
        articleVO.username = article.getUser().getUsername();
        articleVO.typeName = article.getType().getName();

        return articleVO;
    }

    public static Article vo2po(ArticleVo articleVO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleVO, article);
        article.setUser(User.of(articleVO.username));
        article.setType(Type.of(articleVO.typeName));

        return article;
    }

}
