package org.hzz.service;


import org.hzz.entity.Friend;
import org.hzz.mapper.R.RFriendMapper;
import org.hzz.mapper.W.WFriendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {
    @Autowired
    private RFriendMapper rFriendMapper;

    @Autowired
    private WFriendMapper wFriendMapper;

    public List<Friend> list(){
        return  rFriendMapper.list();
    }

    public void save(Friend friend){
         wFriendMapper.save(friend);
    }
}
