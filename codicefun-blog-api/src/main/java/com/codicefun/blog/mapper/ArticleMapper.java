package com.codicefun.blog.mapper;

import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.entity.vo.ArticleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(source = "article.user.username", target = "username")
    @Mapping(source = "article.type.name", target = "typeName")
    ArticleVO po2vo(Article article);

    @Mapping(source = "articleVo.username", target = "user.username")
    @Mapping(source = "articleVo.typeName", target = "type.name")
    Article vo2po(ArticleVO articleVo);

    List<ArticleVO> pos2vos(List<Article> articles);

    List<Article> vos2pos(List<ArticleVO> articleVos);

}
