package com.nenadm.JwtProject;


import com.nenadm.JwtProject.Model.JwtToken;
import com.nenadm.JwtProject.Model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/authenticate")
public class authenticationServer {

    private List<User> userDb;

    @PostMapping
    public String authenticate(@RequestBody User user){

        User user1 = new User("nenadmit","12345");
        User user2 = new User("nenad","12345");
        User user3 = new User("nenad123","12345");
        userDb = Arrays.asList(user1,user2,user3);

        if(!authenticate(user.getUsername(),user.getPassword()))
            return "User not found!";





        return new JwtToken(Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"aSf4#f3d#@").compact()).getToken();
    }



    private boolean authenticate(String username,String password){

        for (User user:userDb){
            if(user.getUsername().equals(username)){
                if(user.getPassword().equals(password))
                    return true;
            }
        }
        return false;


    }

}
