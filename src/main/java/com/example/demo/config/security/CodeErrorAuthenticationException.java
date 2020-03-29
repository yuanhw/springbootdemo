package com.example.demo.config.security;


import org.springframework.security.core.AuthenticationException;

/**
 * CodeErrorAuthenticationException
 *
 * @author Wang Yuanhang
 * @date 2020/3/2811:20
 */
public class CodeErrorAuthenticationException extends AuthenticationException {

    public CodeErrorAuthenticationException(String code) {
        super("code : " + code + " validate error.");
    }
}
