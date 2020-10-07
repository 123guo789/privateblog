package com.blogdemo.privateblog.service;

import com.blogdemo.privateblog.NotFoundException;
import com.blogdemo.privateblog.dao.FriendsLinkRepository;
import com.blogdemo.privateblog.po.FriendsLink;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FriendsLinkServiceImpl implements FriendsLinkService {

    @Autowired
    private FriendsLinkRepository friendsLinkRepository;

    @Transactional
    @Override
    public List<FriendsLink> listFriendsLink() {
        return friendsLinkRepository.findAll();
    }

    @Transactional
    @Override
    public FriendsLink saveFriendsLink(FriendsLink friendsLink) {
        return friendsLinkRepository.save(friendsLink);
    }

    @Transactional
    @Override
    public FriendsLink getFriendsLink(Long id) {
        return friendsLinkRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<FriendsLink> listFriendsLink(Pageable pageable) {
        return friendsLinkRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public FriendsLink updateFriendsLink(Long id, FriendsLink friendsLink) {
        FriendsLink f=friendsLinkRepository.getOne(id);
        if (f==null){
            throw new NotFoundException("不存在该友链");
        }
        BeanUtils.copyProperties(friendsLink,f);
        return friendsLinkRepository.save(f);
    }

    @Transactional
    @Override
    public void deleteFriendsLink(Long id) {
        friendsLinkRepository.deleteById(id);
    }
}
