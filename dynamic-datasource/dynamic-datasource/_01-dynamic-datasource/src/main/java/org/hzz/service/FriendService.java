package org.hzz.service;

import org.hzz.annotation.WR;
import org.hzz.entity.Friend;
import org.hzz.mapper.FriendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @WR("R")
    public List<Friend> list(){
        return friendMapper.list();
    }

    @WR("W")
    public void save(Friend friend){
        friendMapper.save(friend);
    }
}
