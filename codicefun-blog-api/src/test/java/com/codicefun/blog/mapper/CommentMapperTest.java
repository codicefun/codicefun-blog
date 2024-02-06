package com.codicefun.blog.mapper;

import com.codicefun.blog.entity.dto.CommentDto;
import com.codicefun.blog.entity.po.Article;
import com.codicefun.blog.entity.po.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
class CommentMapperTest {

    @Autowired
    CommentMapper mapper;

    @Test
    void po2dto() {
        Article article = Article.builder()
                                 .id(1)
                                 .build();
        Comment parent = Comment.builder()
                                .id(100)
                                .build();
        Comment comment = Comment.builder()
                                 .nickname("Test")
                                 .article(article)
                                 .parent(parent)
                                 .build();
        CommentDto commentDto = mapper.po2dto(comment);

        assertEquals(1, commentDto.getArticleId());
        assertEquals(100, commentDto.getParentId());
        assertEquals("Test", commentDto.getNickname());
    }

    @Test
    void dto2po() {
        CommentDto commentDto = CommentDto.builder()
                                          .id(1)
                                          .articleId(1)
                                          .parentId(1)
                                          .build();
        Comment comment = mapper.dto2po(commentDto);

        assertEquals(1, comment.getId());
        assertEquals(1, comment.getArticle().getId());
        assertEquals(1, comment.getParent().getId());
    }

}