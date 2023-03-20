package org.hzz.mapper.W;

import org.apache.ibatis.annotations.Insert;
import org.hzz.entity.Friend;

public interface WFriendMapper {

    @Insert("INSERT INTO  friend(`name`) VALUES (#{name})")
    void save(Friend friend);
}
