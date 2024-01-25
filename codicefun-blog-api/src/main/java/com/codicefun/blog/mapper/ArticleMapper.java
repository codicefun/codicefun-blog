package com.codicefun.blog.mapper;

import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.entity.vo.ArticleVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(source = "article.user.username", target = "username")
    @Mapping(source = "article.type.name", target = "typeName")
    ArticleVo po2vo(Article article);

    @Mapping(source = "articleVo.username", target = "user.username")
    @Mapping(source = "articleVo.typeName", target = "type.name")
    Article vo2po(ArticleVo articleVo);

    List<ArticleVo> pos2vos(List<Article> articles);

    List<Article> vos2pos(List<ArticleVo> articleVos);

}
