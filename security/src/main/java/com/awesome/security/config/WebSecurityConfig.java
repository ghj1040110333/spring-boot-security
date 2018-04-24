package com.awesome.security.config;

import com.awesome.model.enums.RoleEnum;
import com.awesome.security.AwesomeAuthenticationFailureHandler;
import com.awesome.security.AwesomeAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProvider awesomeAuthenticationProvider;
    private final AuthenticationFailureHandler awesomeAuthenticationFailureHandler;
    private final AccessDeniedHandler awesomeAccessDeniedHandler;

    @Autowired
    public WebSecurityConfig(AwesomeAuthenticationProvider awesomeAuthenticationProvider,
                             AwesomeAuthenticationFailureHandler awesomeAuthenticationFailureHandler,
                             AccessDeniedHandler awesomeAccessDeniedHandler) {
        this.awesomeAuthenticationProvider = awesomeAuthenticationProvider;
        this.awesomeAuthenticationFailureHandler = awesomeAuthenticationFailureHandler;
        this.awesomeAccessDeniedHandler = awesomeAccessDeniedHandler;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/admin/**").hasRole(RoleEnum.ADMIN.getValue())
                .anyRequest().fullyAuthenticated()
                .and().formLogin().loginPage("/login").failureHandler(awesomeAuthenticationFailureHandler).permitAll()
                .and().logout().permitAll();
        http.exceptionHandling().accessDeniedHandler(awesomeAccessDeniedHandler);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        //将验证过程交给自定义验证工具
        auth.authenticationProvider(awesomeAuthenticationProvider);
    }

}
