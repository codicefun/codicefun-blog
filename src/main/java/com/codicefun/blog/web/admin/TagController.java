package com.codicefun.blog.web.admin;

import com.codicefun.blog.entity.Tag;
import com.codicefun.blog.service.TagService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Resource
    private TagService tagService;

    @GetMapping("/tags")
    public String list(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                       Pageable pageable,
                       Model model) {
        model.addAttribute("page", tagService.listTag(pageable));

        return "admin/tags";
    }

    @GetMapping("/tags/add")
    public String add(Model model) {
        model.addAttribute("tag", new Tag());

        return "admin/tags-input";
    }

    @PostMapping("/tags/add")
    public String add(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        if (tagService.getTagByName(tag.getName()) != null) {
            result.rejectValue("name", "nameError", "不能添加重复的标签");
            return "admin/tags-input";
        }

        if (result.hasErrors()) {
            return "admin/tags-input";
        }

        if (tagService.saveTag(tag) == null) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }

        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/update")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));

        return "admin/tags-input";
    }

    @PostMapping("/tags/{id}/update")
    public String update(@Valid Tag tag, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        if (tagService.getTagByName(tag.getName()) != null) {
            result.rejectValue("name", "nameError", "不能添加重复的标签");
            return "admin/tags-input";
        }

        if (result.hasErrors()) {
            return "admin/tags-input";
        }

        if (tagService.updateTag(id, tag) == null) {
            attributes.addFlashAttribute("message", "修改失败");
        } else {
            attributes.addFlashAttribute("message", "修改成功");
        }

        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");

        return "redirect:/admin/tags";
    }

}
