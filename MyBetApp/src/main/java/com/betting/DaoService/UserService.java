package com.betting.DaoService;

import com.betting.MyBet.Model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private Configuration con;
    private SessionFactory sessionFactory;
    private Session session;

    public UserService(){
        con = new Configuration().configure().addAnnotatedClass(Player.class);
        sessionFactory = con.buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public void save(Player player){

        session.getTransaction().begin();
        session.save(player);
        session.flush();
        session.beginTransaction().commit();
        session.clear();
    }

    public void merge(Player player){

        session.getTransaction().begin();
        session.merge(player);
        session.flush();
        session.beginTransaction().commit();
        session.clear();
    }

    public void update(Player player){

        session.getTransaction().begin();
        session.update(player);
        session.getTransaction().commit();
        session.clear();

    }

    public Player getPlayerByUsername(String username){
        Player player;

        player = (Player) session.createQuery(
                "SELECT p FROM player p where p.username=:username")
                .setParameter("username",username).getSingleResult();
        session.clear();
        return player;

    }


    public void manage(){
        if(session == null){
            session = sessionFactory.openSession();
            System.out.println("Session is null, opening session!");

        }else{
            session.close();
        }

    }



}
