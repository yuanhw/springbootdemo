package com.example.demo.resource;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * UserController
 *
 * @author Wang Yuanhang
 * @date 2020/3/21 1:05 下午
 */
@RestController("user")
public class UserController {

    private final UserMapper userMapper;

    @Autowired
    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("getUserInfo")
    public User getUserInfo(@RequestParam Long id) {
        return userMapper.selectById(id);
    }

    @PostMapping("addUserInfo")
    public User addUserInfo(@ModelAttribute User User) {
        this.userMapper.insert(User);
        return User;
    }
}
