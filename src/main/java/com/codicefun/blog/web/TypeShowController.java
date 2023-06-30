package com.codicefun.blog.web;

import com.codicefun.blog.entity.Type;
import com.codicefun.blog.service.BlogService;
import com.codicefun.blog.service.TypeService;
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
public class TypeShowController {

    @Resource
    private TypeService typeService;

    @Resource
    private BlogService blogService;

    @GetMapping("/type/{id}")
    public String types(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                        Pageable pageable, @PathVariable Long id, Model model) {
        List<Type> types = typeService.listTypeTop(10000);

        if (id == -1) { // 从导航进入
            id = types.get(0).getId();
        }

        // BlogQuery query = new BlogQuery();
        // query.setTypeId(id);
        model.addAttribute("types", types);
        // model.addAttribute("page", blogService.listByMultiQuery(query, pageable));
        model.addAttribute("page", blogService.listByType(id, pageable));
        model.addAttribute("activeTypeId", id);

        return "types";
    }

}
