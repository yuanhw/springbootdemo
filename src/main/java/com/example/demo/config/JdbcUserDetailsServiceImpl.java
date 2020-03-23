package com.example.demo.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * JdbcUserDetailsService
 *
 * @author Wang Yuanhang
 * @date 2020/3/22 11:40 上午
 */
@Component
public class JdbcUserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    @Autowired
    public JdbcUserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("username : " + username + " is null.");
        }
        return new JdbcUserDetails(user);
    }

    public User getUserByName(String name) {
        LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .lambda()
                .eq(User::getName, name);

        return this.userMapper.selectOne(queryWrapper);
    }
}
