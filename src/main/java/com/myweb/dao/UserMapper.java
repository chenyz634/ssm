package com.myweb.dao;

import com.myweb.base.dao.BaseMapper;
import com.myweb.entity.User;
import com.myweb.entity.UserExample;
import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> selectUserAndRoleByExample(UserExample example);

}