package org.hzz.controller;

import org.hzz.entity.Friend;
import org.hzz.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @GetMapping(value = "select")
    public List<Friend> select(){
        return friendService.list();
    }


    @GetMapping(value = "insert")
    public String insert(){
        Friend friend = new Friend();
        friend.setName("hzz");
        friendService.save(friend);
        return "data added";
    }

}
