package com.codicefun.blog.service;

import com.codicefun.blog.model.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    /**
     * 保存标签
     *
     * @param tag 待保存的标签对象
     * @return 保存后的标签对象
     */
    Tag saveTag(Tag tag);

    /**
     * 根据 id 获取标签
     *
     * @param id 标签 id
     * @return 标签对象
     */
    Tag getTag(Long id);

    /**
     * 根据标签名获取标签
     *
     * @param name 标签名
     * @return 标签对象
     */
    Tag getTagByName(String name);

    /**
     * 列出所有标签
     *
     * @param pageable 分页接口
     * @return 可分页的标签集合
     */
    Page<Tag> listTag(Pageable pageable);

    /**
     * 列出所有标签
     *
     * @return 标签列表
     */
    List<Tag> listTag();

    /**
     * find tags by tag id string
     *
     * @param ids tag ids string("1,2,3")
     * @return find result
     */
    List<Tag> listTag(String ids);

    /**
     * 最多列出 size 个标签
     *
     * @param size 标签数
     * @return 标签列表
     */
    List<Tag> listTagTop(Integer size);

    /**
     * 更新标签
     *
     * @param id   标签 id
     * @param type 新的标签对象
     * @return 更新后的标签对象
     */
    Tag updateTag(Long id, Tag type);

    /**
     * 删除标签
     *
     * @param id 标签 id
     */
    void deleteTag(Long id);

}
