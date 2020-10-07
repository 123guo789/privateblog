package com.blogdemo.privateblog.service;

import com.blogdemo.privateblog.NotFoundException;
import com.blogdemo.privateblog.dao.TypeRepository;
import com.blogdemo.privateblog.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort.Order sort=new Sort.Order(Sort.Direction.DESC,"blogs.size");
        Pageable pageable= PageRequest.of (0,size,Sort.by(Sort.Direction.DESC,"blogs.size"));
        return typeRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Type update(Long id, Type type) {
        Type t=typeRepository.getOne(id);
        if (t==null){
            throw new NotFoundException("该类型不存在");
        }
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
