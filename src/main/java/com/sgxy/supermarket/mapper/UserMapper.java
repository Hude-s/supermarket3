package com.sgxy.supermarket.mapper;

import com.sgxy.supermarket.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserMapper {
    User login(@Param("userName") String userName, @Param("password") String password);

    List<User> queryUserByName(@Param("userName")String userName);

    Integer addUser(User user);

    Integer updateUser(User user);

    Integer deleteUser(@Param("userId") Integer userId);
}
