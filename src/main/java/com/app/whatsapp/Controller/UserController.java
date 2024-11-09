package com.app.whatsapp.Controller;

import com.app.whatsapp.Entity.User;
import com.app.whatsapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @MessageMapping("/chat.addUser")
    @SendTo("/topic")
    public User addUser(@Payload User user){
       return userService.SaveUser(user);
    }

    @MessageMapping("/chat.disconnectUser")
    @SendTo("/topic")
    public User disConnectUser(@Payload User user){
        return userService.disconnect(user);
    }

    @GetMapping("findAll")
    public ResponseEntity<List<User>> findConnectedUser(){
        return ResponseEntity.ok(userService.findAllConnectedUser());
    }
}
