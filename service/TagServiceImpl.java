package com.blogdemo.privateblog.service;

import com.blogdemo.privateblog.NotFoundException;
import com.blogdemo.privateblog.dao.TagRespository;
import com.blogdemo.privateblog.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRespository tagRespository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRespository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRespository.getOne(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRespository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRespository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagRespository.findAll();
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort.Order sort=new Sort.Order(Sort.Direction.DESC,"blogs.size");
        Pageable pageable= PageRequest.of (0,size,Sort.by(Sort.Direction.DESC,"blogs.size"));
        return tagRespository.findTop(pageable);
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagRespository.findAllById(convertToList(ids));
    }

    private List<Long> convertToList(String ids){
        List<Long> list=new ArrayList<>();
        if (!"".equals(ids)&&ids!=null){
            String[] idarray=ids.split(",");
            for (int i=0;i<idarray.length;i++){
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t=tagRespository.getOne(id);
        if (t==null){
            throw new NotFoundException("该标签不存在");
        }
        BeanUtils.copyProperties(tag,t);
        return tagRespository.save(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRespository.deleteById(id);
    }
}
