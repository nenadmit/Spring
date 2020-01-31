package com.example.SpringMessageApp.Controllers;

import com.example.SpringMessageApp.UserDetails.PrincipalUser;
import com.example.SpringMessageApp.model.User;
import com.example.SpringMessageApp.model.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    public User user;
    public HttpSession session;
    @Autowired
    public UserRepository userRepo;

    @GetMapping(path="/login")
    public String getIndex(){

        return "login";

    }


    @PostMapping(path="/sucess")
    public String sucess(HttpSession session){

        PrincipalUser loggedUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        session.setAttribute("username",loggedUser.getUsername());
        session.setAttribute("name",loggedUser.getName());

        return "redirect:/messages";
    }





    }


