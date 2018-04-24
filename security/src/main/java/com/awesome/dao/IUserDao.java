package com.awesome.dao;


import com.awesome.model.User;

import java.util.List;

public interface IUserDao {
    User findUserByName(String username);

    List<String> findUserAuthoritiesByName(String username);
}
