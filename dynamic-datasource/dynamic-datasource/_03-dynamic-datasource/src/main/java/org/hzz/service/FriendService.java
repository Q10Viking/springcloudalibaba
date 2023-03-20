package org.hzz.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.hzz.entity.Friend;
import org.hzz.mapper.FriendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @DS("slave_1")
    public List<Friend> list(){
        return friendMapper.list();
    }

    @DS("master")
    public void save(Friend friend){
        friendMapper.save(friend);
    }
}
