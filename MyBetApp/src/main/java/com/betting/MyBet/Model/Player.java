package com.betting.MyBet.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="player")

@Table(name="players")
public class Player {

    @Id
    @Column(name="player_id")
    private int playerId;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @OneToMany(mappedBy = "playerr",fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @Column(nullable = false)
    @JsonManagedReference
    private List<Ticket> tickets = new ArrayList<>();

    public Player(){

    }

    public Player(String username,String password){
        this.username = username;
        this.password = password;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
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

    public List<Ticket> addTicket() {
        return tickets;
    }




}


