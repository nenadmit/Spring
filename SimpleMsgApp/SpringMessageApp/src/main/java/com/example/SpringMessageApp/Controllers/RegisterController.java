package com.example.SpringMessageApp.Controllers;

import com.example.SpringMessageApp.model.UserRepository;
import com.example.SpringMessageApp.model.User;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class RegisterController {

    public User user;
    @Autowired
    public UserRepository userRepo;
    public PasswordEncoder passwordEncoder;


    @GetMapping(path="/register")
    public String RegistartionPage(Model model,HttpSession session){
        user = new User();
        model.addAttribute("user",user);


        return "register";
    }
    @PostMapping(path="/register")
    public String saveUserToDb(@ModelAttribute @Valid User user,BindingResult result,Model model,HttpSession session){
        if(result.hasErrors()){
            return "register";
        }
        passwordEncoder = new BCryptPasswordEncoder();

        String email = user.getEmail();
        String username = user.getUsername();

        if(isUsernameTaken(username)){
            model.addAttribute("userError","Username already taken!");
            return "register";
        }


        else if(isEmailTaken(email)){
            model.addAttribute("emailError","Email already taken!");
            return "register";
        }



        user = new User(username,passwordEncoder.encode(user.getPassword()),
                email,user.getName(),user.getSurname(),user.getSecretQuestion(),user.getSecretAnswer());


        userRepo.save(user);

        model.addAttribute("sucessfulReg","Registration sucessful!");



        return "login";
    }


    @GetMapping(path="/welcome")
    public String returnWelcomePage(Model model, HttpSession session){



        return "welcome";

    }
    @PostMapping(path="/welcome")
    public String returnWelcome(Model model){

        return "welcome";



    }



    public boolean isEmailTaken(String email){
        User user = userRepo.findByEmail(email);
        return user!=null;
    }

    public boolean isUsernameTaken(String username){
        User user = userRepo.findByUsername(username);
        return user!=null;

    }


    }


