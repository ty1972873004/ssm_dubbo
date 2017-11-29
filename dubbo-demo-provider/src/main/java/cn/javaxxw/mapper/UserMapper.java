package cn.javaxxw.mapper;

import java.util.List;

import cn.javaxxw.model.User;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2017-11-29 15:04
 **/
public interface UserMapper {

    /**
     * 插入记录
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 查询所有记录
     * @return
     */
    List<User> selectAll();


}
