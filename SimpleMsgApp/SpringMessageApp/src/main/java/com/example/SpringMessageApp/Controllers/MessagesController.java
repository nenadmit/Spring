package com.example.SpringMessageApp.Controllers;

import com.example.SpringMessageApp.model.Messages;
import com.example.SpringMessageApp.model.MsgRepository;

import com.example.SpringMessageApp.model.User;
import com.example.SpringMessageApp.model.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.util.*;


@Controller
public class MessagesController {



    private Messages message;
    @Autowired
    private MsgRepository msgRepo;
    @Autowired
    private UserRepository userRepo;

    @GetMapping(path="/messages")
    public String getInbox(Model model, HttpSession session){

        message = new Messages();
        model.addAttribute("message",message);
        model.addAttribute("user",session.getAttribute("username"));

        return "messages";
    }
    @PostMapping(path="/messages")
    public String sendMessage(@ModelAttribute("message") Messages message, Model model,
                              BindingResult result, HttpSession session){

        User receiver = userRepo.findByUsername(message.getReceiver());
        if(receiver!=null){
            String s = (String) session.getAttribute("username");

            message = new Messages(message.getTitle(), s ,message.getReceiver(),message.getMsgBody());
            msgRepo.save(message);

            System.out.println(session.getAttribute("username"));

            model.addAttribute("sucess","Message has been sent!");
            return "messages";

        }
        String s = "User + " + message.getReceiver() + " doesn't exist in a database!";
        model.addAttribute("sucess",s);

        return "messages";
    }
    @GetMapping(path="/sent")
    public String sendRecievedMessages(Model model, HttpSession session){


        ArrayList<Messages>arr2 = new ArrayList<>();
        String sender = (String) session.getAttribute("username");
        Iterable<Messages> arr = msgRepo.findAllBySender(sender);

        arr.forEach(messages -> arr2.add(messages));
        model.addAttribute("arr2",reverseArray(arr2));

        return "sent";
    }

    @GetMapping(path="/inbox")
    public String sendSentMessages(Model model, HttpSession session){

        ArrayList<Messages>arr2 = new ArrayList<>();
        String receiver = (String) session.getAttribute("username");
        Iterable<Messages> arr = msgRepo.findAllByReceiver(receiver);




        arr.forEach(messages -> arr2.add(messages));
        model.addAttribute("arr2",reverseArray(arr2));

        return "inbox";
    }
    @GetMapping(path="/delete")
    public String deleteMsg(@RequestParam int id){
        msgRepo.deleteById(id);
        return "redirect:/inbox";
    }




    //* Reversing an ArrayList of messages since i couldn't get LinkedList to show on thymeleaf template with th:forEach element
    public ArrayList<Messages> reverseArray(ArrayList<Messages> arr){

        Stack<Messages> stack = new Stack<>();
        arr.forEach(msg->stack.push(msg));
        ArrayList<Messages> reversedArr = new ArrayList<>();
        for ( var a:arr){
            reversedArr.add(stack.pop());

        }


        return reversedArr;
    }



}
