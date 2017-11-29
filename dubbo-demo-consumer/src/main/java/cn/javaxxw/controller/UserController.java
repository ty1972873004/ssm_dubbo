package cn.javaxxw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.javaxxw.model.User;
import cn.javaxxw.service.UserService;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2017-11-29 15:38
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public Object register(String userName,String password,String nickName){
        User user = userService.addUser(userName,password,nickName);
        return user.toString();
    }

}
