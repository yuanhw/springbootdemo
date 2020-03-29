package com.example.demo.resource;

import com.example.demo.config.security.JdbcUserDetailsServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * UserController
 *
 * @author Wang Yuanhang
 * @date 2020/3/21 1:05 下午
 */
@Controller
@RequestMapping("user")
public class UserController {

    private final UserMapper userMapper;

    private final JdbcUserDetailsServiceImpl jdbcUserDetailsService;

    @Autowired
    public UserController(UserMapper userMapper, JdbcUserDetailsServiceImpl jdbcUserDetailsService) {
        this.userMapper = userMapper;
        this.jdbcUserDetailsService = jdbcUserDetailsService;
    }

    @GetMapping("getUserInfo")
    @ResponseBody
    public User getUserInfo(@RequestParam Long id) {
        return userMapper.selectById(id);
    }

    @PostMapping("addUserInfo")
    @ResponseBody
    public User addUserInfo(@ModelAttribute User User) {
        this.userMapper.insert(User);
        return User;
    }

    @GetMapping("demoLogin")
    public String demoLogin() {
        return "demoLogin";
    }

    @GetMapping("myInfo")
    public String myInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = jdbcUserDetailsService.getUserByName(authentication.getName());
        model.addAttribute("demoUser", user);
        return "myInfo";
    }

    @GetMapping("loginError")
    public String loginErr() {
        return "loginError";
    }
}
