package com.codicefun.blog.service;

import com.codicefun.blog.dao.TagRepository;
import com.codicefun.blog.exception.NotFoundException;
import com.codicefun.blog.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagRepository.findOne(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }


    /**
     * 根据 id 字符串，返回 Tag 列表
     *
     * @param ids id 字符串，"1,2,3"
     * @return 选中的 Tag 列表
     */
    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAll(coverToList(ids));
    }

    /**
     * 将 id 字符串转为对应的 Tag 列表
     *
     * @param ids id 字符串，"1,2,3"
     * @return Tag 列表
     */
    private List<Long> coverToList(String ids) {
        ArrayList<Long> list = new ArrayList<>();

        if (ids != null && !ids.equals("")) {
            String[] split = ids.split(",");
            for (String s : split) {
                list.add(new Long(s));
            }
        }

        return list;
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.findOne(id);

        if (t == null) {
            throw new NotFoundException("不存在该分类");
        }

        BeanUtils.copyProperties(tag, t);

        return tagRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.delete(id);
    }
}
