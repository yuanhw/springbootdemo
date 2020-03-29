package com.example.demo.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CustomAuthenticationFilter
 *
 * @author Wang Yuanhang
 * @date 2020/3/2810:46
 */
public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String V_CODE = "SPRING-SECURITY";

    private static final String V_CODE_PARAMETER = "verificationCode";

    public CustomAuthenticationFilter() {
        super(new AntPathRequestMatcher("/user/doDemoLogin", "POST"));
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (!requiresAuthentication(request, response)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            if (attemptAuthentication(request, response) == null) {
                chain.doFilter(request, response);
            }
        } catch (AuthenticationException failed) {
            // Authentication failed
            unsuccessfulAuthentication(request, response, failed);
        }
    }

    private void checkVerificationCode(HttpServletRequest request) throws CodeErrorAuthenticationException {
        String verificationCode = request.getParameter(V_CODE_PARAMETER);
        if (!verificationCode.equalsIgnoreCase(V_CODE)) {
            throw new CodeErrorAuthenticationException(verificationCode);
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        checkVerificationCode(request);
        return null;
    }
}
