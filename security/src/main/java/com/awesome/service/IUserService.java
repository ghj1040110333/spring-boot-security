package com.awesome.service;

import com.awesome.model.User;

import java.util.List;

public interface IUserService {

    User findUserByName(String username);

    List<String> findUserAuthoritiesByName(String username);
}
