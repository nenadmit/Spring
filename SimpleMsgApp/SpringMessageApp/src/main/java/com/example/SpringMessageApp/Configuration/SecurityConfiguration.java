package com.example.SpringMessageApp.security;

import com.example.SpringMessageApp.model.UserRepository;
import com.example.SpringMessageApp.UserDetails.MyUserDetailService;

import com.example.SpringMessageApp.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserRepository userRepo;
    @Autowired
    public MyUserDetailService userService;


    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().authoritiesByUsernameQuery("select username,password from users_table where username=username");
        auth.authenticationProvider(DaoAuth());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/login").permitAll()
                .and().authorizeRequests().antMatchers("/register").permitAll()
                .and().authorizeRequests().antMatchers("/**").hasAuthority("USER")
                .and().formLogin().loginPage("/login").successForwardUrl("/sucess")
                .and().csrf().disable();

    }
    @Bean
    DaoAuthenticationProvider DaoAuth(){
        DaoAuthenticationProvider daoAuth = new DaoAuthenticationProvider();
        daoAuth.setPasswordEncoder(passwordEncoder());
        daoAuth.setUserDetailsService(userService);

        return daoAuth;
    }

     PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
     }



}
