package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * DemoWebSecurityConfigurerAdapter
 *
 * @author Wang Yuanhang
 * @date 2020/3/21 4:49 下午
 */
@Configuration
public class DemoWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final JdbcUserDetailsServiceImpl jdbcUserDetailsService;

    @Autowired
    public DemoWebSecurityConfigurerAdapter(JdbcUserDetailsServiceImpl jdbcUserDetailsService) {
        this.jdbcUserDetailsService = jdbcUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                    .loginPage("/user/demoLogin")
                    .loginProcessingUrl("/user/doDemoLogin")
                    .failureUrl("/user/loginError.html")
                    .permitAll()
                .and().authorizeRequests()
                    .antMatchers("/css/*", "/js/*")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and().csrf()
                    .requireCsrfProtectionMatcher(request -> HttpMethod.POST.name().equals(request.getMethod()));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider(jdbcUserDetailsService));
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(JdbcUserDetailsServiceImpl jdbcUserDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(jdbcUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return charSequence.toString().equals(s);
            }
        });
        return daoAuthenticationProvider;
    }
}
