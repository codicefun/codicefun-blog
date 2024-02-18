package com.codicefun.blog.mapper;

import com.codicefun.blog.entity.dto.ArticleDto;
import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.entity.po.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ArticleMapper {

    @Named("tag2string")
    default List<String> tag2string(List<Tag> tagList) {
        if (tagList == null) {
            return null;
        }

        List<String> tagNameList = new ArrayList<>();

        for (Tag tag: tagList) {
            tagNameList.add(tag.getName());
        }

        return tagNameList;
    }

    @Named("string2tag")
    default List<Tag> string2tag(List<String> tagNameList) {
        if (tagNameList == null) {
            return null;
        }

        List<Tag> tagList = new ArrayList<>();

        for (String tagName: tagNameList) {
            Tag tag = new Tag();
            tag.setName(tagName);
            tagList.add(tag);
        }

        return tagList;
    }

    // TODO: Don't mapping type and tag, they'll be reset in service

    @Mapping(target = "username", source = "article.user.username")
    @Mapping(target = "typename", source = "article.type.name")
    @Mapping(target = "tagNameList", source = "article.tagList", qualifiedByName = "tag2string")
    ArticleDto po2dto(Article article);

    @Mapping(target = "user.username", source = "articleDto.username")
    @Mapping(target = "type.name", source = "articleDto.typename")
    @Mapping(target = "tagList", source = "articleDto.tagNameList", qualifiedByName = "string2tag")
    Article dto2po(ArticleDto articleDto);

    // TODO: change param name to articleList
    List<ArticleDto> poList2dtoList(List<Article> articles);

    List<Article> dtoList2poList(List<ArticleDto> articleDtoList);

}
