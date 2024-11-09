package com.app.whatsapp.Service;

import com.app.whatsapp.Dao.MessageDao;
import com.app.whatsapp.Entity.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageDao messageDao;

    private final ChatRoomService chatRoomService;

    public MessageService(MessageDao messageDao, ChatRoomService chatRoomService) {
        this.messageDao = messageDao;
        this.chatRoomService = chatRoomService;
    }

    public Message save(Message message){
       String chatId = chatRoomService.getChatRoomId(message.getSenderId(), message.getRecipientId(), true).orElseThrow();
       message.setChatId(chatId);
       return messageDao.save(message);
    }

    public List<Message> findChatMessages(String senderId, String recipientId){
       Optional<String> chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
       return chatId.map(messageDao :: findByChatId).orElse(new ArrayList<>());
    }
}
