package com.awesome.mapper;

import com.awesome.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE username = #{username}")
    User findUserByName(@Param("username") String username);

    @Select("SELECT authority FROM authorities WHERE username = #{username}")
    List<String> findUserAuthoritiesByName(@Param("username") String username);
}
