package com.example.SpringMessageApp.UserDetails;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuth implements GrantedAuthority {
    private String authority;

    public GrantedAuth(String s){
        this.authority = s;
    }


    @Override
    public String getAuthority() {
        return authority;
    }
}
