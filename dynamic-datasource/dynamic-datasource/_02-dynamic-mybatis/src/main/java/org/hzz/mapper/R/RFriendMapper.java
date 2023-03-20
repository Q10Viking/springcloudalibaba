package org.hzz.mapper.R;

import org.apache.ibatis.annotations.Select;
import org.hzz.entity.Friend;

import java.util.List;

public interface RFriendMapper {
    @Select("SELECT * FROM friend")
    List<Friend> list();
}
