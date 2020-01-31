package com.example.SpringMessageApp.Controllers;

import com.example.SpringMessageApp.UserDetails.PrincipalUser;
import com.example.SpringMessageApp.model.User;
import com.example.SpringMessageApp.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;





@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepo;
    private boolean usernameClick = false;
    private boolean passwordClick = false;
    private boolean emailClick = false;


    @GetMapping(path="/profile")
    public String getProfile(Model model, HttpSession sesion){

        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();



        model.addAttribute("username",sesion.getAttribute("username"));
        model.addAttribute("user",principalUser);




        model.addAttribute("usernameClick",usernameClick);
        model.addAttribute("passwordClick",passwordClick);
        model.addAttribute("emailClick",emailClick);


        return "profile";
    }
    @PostMapping(path="/show_username_input")
    public String showUsernameInput() {
        usernameClick = !usernameClick;
        return "redirect:/profile";
    }

    @PostMapping(path="/change_username")
    public String changeUsername(@RequestParam String newUsername, Model model, HttpSession session){ ;
           User user = userRepo.findByUsername((String) session.getAttribute("username"));
           user.getUsername();
            user.setUsername(newUsername);

            userRepo.save(user);
            model.addAttribute("nameChangeSucessful","Username sucessfuly changed, please log in again!");
            session.invalidate();


        return "login";






    }
    @PostMapping(path="/show_password_input")
    public String changePasswordInput(){
        passwordClick = !passwordClick;
        return "redirect:/profile";
    }
    @PostMapping(path="/change_password")
    public String changePassword(@RequestParam String password,@RequestParam String newPassword,
                              HttpSession session,Model model){
        if(newPassword.length()<6){
            model.addAttribute("sucessfulPass","Password must be longer than 6 characters!");
            return "redirect:/profile";
        }

        User user = userRepo.findByUsername((String) session.getAttribute("username"));
        var encoder = new BCryptPasswordEncoder();
        if(encoder.matches(password,user.getPassword())){
            user.setPassword(encoder.encode(newPassword));
            userRepo.save(user);
            model.addAttribute("sucessfulPass","Password sucessfuly changed!");
            return "redirect:/profile";
        }
        model.addAttribute("sucessfulPass","Your old password doesn't match!");




        return "profile";
    }
    @PostMapping(path="/show_email_input")
    public String changeEmailInput(){
        emailClick = !emailClick;
        return "redirect:/profile";
    }
    @PostMapping(path="/change_email")
    public String changeEmail(Model model, HttpSession session,
                              @RequestParam String email){
       if(email.length()<6){
           model.addAttribute("sucesfulEmail","Invalid input");
       }
        User userr = userRepo.findByUsername((String) session.getAttribute("username"));
        userr.setEmail(email);
        userRepo.save(userr);
        model.addAttribute("sucesfulEmail","Email adress changed sucessfuly!");
        return "redirect:/profile";



    }

}
