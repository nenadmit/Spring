package com.example.SpringMessageApp.UserDetails;

import com.example.SpringMessageApp.model.User;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalUser implements UserDetails {

    private User user;

    public PrincipalUser(User user){
        this.user= user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var arr = new ArrayList<GrantedAuthority>();
        arr.add(new GrantedAuth("USER"));

        return arr;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName(){
        return user.getName();
    }
    public String getSurname(){
        return user.getSurname();
    }
}
