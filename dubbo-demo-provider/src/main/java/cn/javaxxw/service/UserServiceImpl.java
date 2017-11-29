package cn.javaxxw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import cn.javaxxw.mapper.UserMapper;
import cn.javaxxw.model.User;
import cn.javaxxw.utils.MD5Util;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2017-11-29 14:50
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User addUser(String userName,String password,String nickName) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(MD5Util.MD5(password));
        user.setNickName(nickName);
        userMapper.insert(user);
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return userMapper.selectAll();
    }
}
