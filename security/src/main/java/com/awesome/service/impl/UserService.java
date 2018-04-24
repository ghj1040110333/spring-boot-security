package com.awesome.service.impl;

import com.awesome.dao.IUserDao;
import com.awesome.model.User;
import com.awesome.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User findUserByName(String username) {
        return userDao.findUserByName(username);
    }

    @Override
    public List<String> findUserAuthoritiesByName(String username) {
        return userDao.findUserAuthoritiesByName(username);
    }
}
