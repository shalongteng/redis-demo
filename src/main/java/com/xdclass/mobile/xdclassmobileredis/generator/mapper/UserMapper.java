package com.xdclass.mobile.xdclassmobileredis.generator.mapper;

import com.xdclass.mobile.xdclassmobileredis.generator.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}