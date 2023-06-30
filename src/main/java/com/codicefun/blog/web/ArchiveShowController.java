package com.codicefun.blog.web;

import com.codicefun.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@Controller
public class ArchiveShowController {

    @Resource
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archiveMap", blogService.getArchive());
        model.addAttribute("blogCount", blogService.countPublished());

        return "archives";
    }

}
