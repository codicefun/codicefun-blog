package com.codicefun.blog.web.admin;

import com.codicefun.blog.entity.Blog;
import com.codicefun.blog.entity.User;
import com.codicefun.blog.service.BlogService;
import com.codicefun.blog.service.TagService;
import com.codicefun.blog.service.TypeService;
import com.codicefun.blog.vo.BlogQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@SuppressWarnings("SpringMVCViewInspection")
@Controller
@RequestMapping("/admin")
public class BlogController {

    public static final String INPUT = "admin/blogs-input";
    public static final String LIST = "admin/blogs";
    public static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Resource
    private BlogService blogService;

    @Resource
    private TypeService typeService;

    @Resource
    private TagService tagService;

    /**
     * 设置可供博客选择的分类和标签列表
     */
    private void setTypeAndTags(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }

    /**
     * 显示博客列表
     */
    @GetMapping("/blogs")
    public String list(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                       Pageable pageable, BlogQuery query, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listByMultiQuery(query, pageable));

        return LIST;
    }

    /**
     * 分页，多条件查询
     */
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                         Pageable pageable, BlogQuery query, Model model) {
        model.addAttribute("page", blogService.listByMultiQuery(query, pageable));

        return "admin/blogs :: blogList";
    }

    /**
     * 新增博客页面
     */
    @GetMapping("/blogs/add")
    public String add(Model model) {
        model.addAttribute("blog", new Blog());
        setTypeAndTags(model);

        return INPUT;
    }

    /**
     * 新增博客操作，提交表单
     */
    @PostMapping("/blogs/add")
    public String add(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user")); // 设置用户为当前登录用户
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));

        if (blogService.saveBlog(blog) == null) {
            attributes.addFlashAttribute("message", "添加失败");
        } else {
            attributes.addFlashAttribute("message", "添加成功");
        }

        return REDIRECT_LIST;
    }

    /**
     * 修改博客页面，根据路径参数获取博客 id，
     */
    @GetMapping("/blogs/{id}/update")
    public String update(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlog(id);
        blog.init(); // 博客的一下额外操作，如：将标签列表转为前端需要的 ids 字符串
        model.addAttribute("blog", blog);
        setTypeAndTags(model);

        return INPUT;
    }

    /**
     * 修改博客操作，根据根据路径参数获取博客 id
     */
    @PostMapping("blogs/{id}/update")
    public String update(Blog blog, @PathVariable Long id, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user")); // 设置用户为当前登录用户
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));

        if (blogService.updateBlog(id, blog) == null) {
            attributes.addFlashAttribute("message", "修改失败");
        } else {
            attributes.addFlashAttribute("message", "修改成功");
        }

        return REDIRECT_LIST;
    }

    @GetMapping("blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");

        return REDIRECT_LIST;
    }

}
