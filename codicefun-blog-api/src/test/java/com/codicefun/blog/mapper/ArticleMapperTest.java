package com.codicefun.blog.mapper;

import com.codicefun.blog.entity.dto.ArticleDto;
import com.codicefun.blog.entity.dto.CommentDto;
import com.codicefun.blog.entity.po.*;
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
        List<Comment> commentList = List.of(
                Comment.builder().content("Test1").build(),
                Comment.builder().content("Test2").build(),
                Comment.builder().content("Test3").build());
        Article article = Article.builder()
                                 .id(1)
                                 .user(user)
                                 .type(type)
                                 .tagList(tagList)
                                 .commentList(commentList)
                                 .build();
        ArticleDto articleDto = mapper.po2dto(article);

        assertEquals(1, articleDto.getId());
        assertEquals("admin", articleDto.getUsername());
        assertEquals("Test", articleDto.getTypename());

        List<String> tagNameList = List.of("Test1", "Test2", "Test3");
        List<CommentDto> commentDtoList = List.of(
                CommentDto.builder().content("Test1").build(),
                CommentDto.builder().content("Test2").build(),
                CommentDto.builder().content("Test3").build());

        assertEquals(tagNameList, articleDto.getTagNameList());
        assertEquals(commentDtoList, articleDto.getCommentDtoList());
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
