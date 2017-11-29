package cn.javaxxw.service;

import java.util.List;

import cn.javaxxw.model.User;

public interface UserService {

    /**
     *新增用户
     * @param
     * @return
     */
    User addUser(String userName,String password,String nickName);

    /**
     *查询所有用户
     * @return
     */
    List<User> findAllUsers();
}
