package com.codicefun.blog.mapper;

import com.codicefun.blog.entity.dto.ArticleDto;
import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.entity.po.Tag;
import com.codicefun.blog.entity.po.Type;
import com.codicefun.blog.entity.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
class ArticleMapperTest {

    @Autowired
    ArticleMapper mapper;

    @Test
    void po2dtoTest() {
        User user = User.admin();
        Type type = Type.of("Test");
        List<Tag> tagList = List.of(Tag.of("Test1"), Tag.of("Test2"), Tag.of("Test3"));
        Article article = Article.builder()
                                 .id(1)
                                 .user(user)
                                 .type(type)
                                 .tagList(tagList)
                                 .build();
        ArticleDto articleDto = mapper.po2dto(article);

        assertEquals(1, articleDto.getId());
        assertEquals("admin", articleDto.getUsername());
        assertEquals("Test", articleDto.getTypename());

        List<String> tagNameList = List.of("Test1", "Test2", "Test3");

        assertEquals(tagNameList, articleDto.getTagNameList());
    }

    @Test
    void dto2poTest() {

    }

    @Test
    void poList2dtoListTest() {

    }

    @Test
    void dtoList2poListTest() {

    }

}
