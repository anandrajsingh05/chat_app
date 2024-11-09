package com.app.whatsapp.Service;

import com.app.whatsapp.Dao.ChatRoomDao;
import com.app.whatsapp.Entity.ChatRoom;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {

    private final ChatRoomDao chatRoomDao;

    public ChatRoomService(ChatRoomDao chatRoomDao) {
        this.chatRoomDao = chatRoomDao;
    }

    public Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExists){
        return chatRoomDao.findBySenderIdAndRecipientId(senderId, recipientId).map(ChatRoom::getChatId).or(()-> {
            if(createNewRoomIfNotExists){
                String chatRoomId = createChatRoomId(senderId, recipientId);
                return Optional.of(chatRoomId);
            }
           return Optional.empty();
        });
    }

    public String createChatRoomId(String senderId, String recipientId){
        String chatId = String.format("%s_%s", senderId, recipientId);
        ChatRoom senderRecipient = ChatRoom.builder().chatId(chatId).senderId(senderId).recipientId(recipientId).build();
        ChatRoom recipientSender = ChatRoom.builder().chatId(chatId).senderId(recipientId).recipientId(senderId).build();
        chatRoomDao.save(senderRecipient);
        chatRoomDao.save(recipientSender);
        return chatId;
    }
}
