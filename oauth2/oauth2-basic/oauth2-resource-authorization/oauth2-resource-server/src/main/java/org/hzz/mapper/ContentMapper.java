package org.hzz.mapper;

import org.apache.ibatis.annotations.Select;
import org.hzz.bean.Content;

import java.util.List;

public interface ContentMapper {
    @Select("select * from tb_content where id=#{id}")
    Content getById(Long id);

    @Select("select * from tb_content")
    List<Content> selectAll();
}
