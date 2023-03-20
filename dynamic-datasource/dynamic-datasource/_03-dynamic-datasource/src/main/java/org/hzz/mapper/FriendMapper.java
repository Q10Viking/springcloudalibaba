package org.hzz.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.hzz.entity.Friend;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendMapper {
    @Select("SELECT * FROM friend")
    List<Friend> list();

    @Insert("INSERT INTO  friend(`name`) VALUES (#{name})")
    void save(Friend friend);
}
