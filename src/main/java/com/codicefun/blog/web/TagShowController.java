package com.codicefun.blog.web;

import com.codicefun.blog.po.Tag;
import com.codicefun.blog.service.BlogService;
import com.codicefun.blog.service.TagService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TagShowController {

    @Resource
    private TagService tagService;

    @Resource
    private BlogService blogService;

    @GetMapping("/tag/{id}")
    public String types(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                        Pageable pageable, @PathVariable Long id, Model model) {
        List<Tag> tags = tagService.listTagTop(10000);

        if (id == -1) { // 从导航进入
            id = tags.get(0).getId();
        }

        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.listBlog(id, pageable));
        model.addAttribute("activeTagId", id);

        return "tags";
    }

}