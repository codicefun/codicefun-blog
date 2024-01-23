package com.codicefun.blog.entity.vo;

import com.codicefun.blog.entity.po.Article;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class ArticleVO {

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

    public static ArticleVO of(Article article) {
        ArticleVO articleVO = new ArticleVO();
        BeanUtils.copyProperties(article, articleVO);
        articleVO.username = article.getUser().getUsername();
        articleVO.typeName = article.getType().getName();

        return articleVO;
    }

}
