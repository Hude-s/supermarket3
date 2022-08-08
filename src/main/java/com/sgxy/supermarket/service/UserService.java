package com.sgxy.supermarket.service;

import com.github.pagehelper.PageHelper;
import com.sgxy.supermarket.entity.User;
import com.sgxy.supermarket.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User userLogin(String userName, String password) {
        return userMapper.login(userName, password);
    }

    public List<User> queryUserByName(int page, int size, String userName) {
        PageHelper.startPage(page, size);
        return userMapper.queryUserByName(userName);
    }

    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }


    public Integer deleteUser(Integer userId) {
        return userMapper.deleteUser(userId);
    }

    public Integer updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public boolean deleteUserBatch(String userIds) {
        String[] ids = userIds.split(",");
        boolean flag = false;
        int count = 0;
        for (String id : ids) {
            count = userMapper.deleteUser(Integer.parseInt(id));
        }
        if (count > 0) {
            flag = true;
        }
        return flag;
    }
}
