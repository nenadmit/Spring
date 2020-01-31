package com.example.SpringMessageApp.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MsgRepository extends CrudRepository<Messages,Integer> {
    Messages findBySender(String sender);
    Messages findByReceiver(String receiver);
    Messages findByTitle(String title);
    Iterable<Messages> findAllBySender(String sender);
    Iterable<Messages> findAllByReceiver(String receiver);
    

}
