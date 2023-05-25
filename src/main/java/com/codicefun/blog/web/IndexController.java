package com.codicefun.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/{id}/{name}")
    public String index(@PathVariable String id, @PathVariable String name) {
        System.out.println(id + ", " + name);

        return "index";
    }

}
