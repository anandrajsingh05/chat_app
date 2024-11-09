package com.app.whatsapp.Dao;

import com.app.whatsapp.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao extends JpaRepository<Message, String> {

    List<Message> findByChatId(String chatId);
}
