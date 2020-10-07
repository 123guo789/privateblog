package com.blogdemo.privateblog.service;

import com.blogdemo.privateblog.po.FriendsLink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FriendsLinkService {

    List<FriendsLink> listFriendsLink();

    FriendsLink saveFriendsLink(FriendsLink friendsLink);

    FriendsLink getFriendsLink(Long id);

    Page<FriendsLink> listFriendsLink(Pageable pageable);

    FriendsLink updateFriendsLink(Long id,FriendsLink friendsLink);

    void deleteFriendsLink(Long id);
}
