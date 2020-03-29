package com.example.demo.config.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

/**
 * JdbcUserDetails
 *
 * @author Wang Yuanhang
 * @date 2020/3/22 11:42 上午
 */
public class JdbcUserDetails extends User  {

    private final com.example.demo.entity.User user;

    public JdbcUserDetails(com.example.demo.entity.User user) {
        super(user.getName(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("all")));
        this.user = user;
    }

    public com.example.demo.entity.User getUser() {
        return user;
    }
}
