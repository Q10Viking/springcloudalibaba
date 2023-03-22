package org.hzz.mapper;

import org.hzz.model.Methodlock;

public interface MethodlockMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByMethodlock(String methodName);

    int insert(Methodlock record);

    int insertSelective(Methodlock record);

    Methodlock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Methodlock record);

    int updateByPrimaryKey(Methodlock record);
}
