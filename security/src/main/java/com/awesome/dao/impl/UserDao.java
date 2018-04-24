package com.awesome.dao.impl;

import com.awesome.dao.IUserDao;
import com.awesome.model.User;
import com.awesome.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao implements IUserDao {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public List<String> findUserAuthoritiesByName(String username) {
        return userMapper.findUserAuthoritiesByName(username);
    }
}
