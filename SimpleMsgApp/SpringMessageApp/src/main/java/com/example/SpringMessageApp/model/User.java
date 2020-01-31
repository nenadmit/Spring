package com.example.SpringMessageApp.model;


import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Entity
@Table(name="Mailer_Users")
public class User {



    public User(@Size(min = 6)String username, @Size(min = 6) String password, @NotBlank @Email String email, @NotBlank String name,
                @NotNull String surname, String secretQuestion, @NotBlank String secretAnswer) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
        setRole("USER");



    }

    public User() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Size(min = 6,message="Username should be more than 6 characters")
    @Column(name="username",unique = true)
    @NotBlank
    public String username;

    @Size(min=6,message = "password should be longer than 6 characters")
    @Column(name="password")
    @NotBlank
    public String password;

    @Column(name="email",unique = true)
    @Email
    @NotBlank
    public String email;

    @NotBlank
    public String name;
    @NotBlank
    public String surname;

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    public String secretQuestion;

    @NotBlank
    public String secretAnswer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name="role")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }




}
