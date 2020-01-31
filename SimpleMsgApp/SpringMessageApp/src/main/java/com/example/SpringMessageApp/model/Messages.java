package com.example.SpringMessageApp.model;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;

@Entity
@Table(name="user_messages")
public class Messages  {

    public Messages(String title, String sender, String receiver, String msgBody) {
        this.title = title;
        this.sender = sender;
        this.receiver = receiver;
        this.msgBody = msgBody;
    }
    public Messages(){

    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int Id;

    public String title;

    public String sender;
    public String receiver;
    public String msgBody;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return title + " " + sender + " " + receiver + " " + msgBody;
    }
}
