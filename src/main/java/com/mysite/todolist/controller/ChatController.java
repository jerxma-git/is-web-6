package com.mysite.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.mysite.todolist.model.Message;
import com.mysite.todolist.model.User;
import com.mysite.todolist.service.UserService;

@Controller
public class ChatController {

    @Autowired
    UserService userService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message) {
        // User user = userService.getCurrentUser();
        // if (message.getSenderUsername() == null) {
            // message.setSenderUsername(user == null ? "Anonymus" : user.getUsername());
        // }
        return message;
    }

    @MessageMapping("/chat.sendAsAdmin")
    @SendTo("/topic/messages")
    public Message sendAsAdmin(Message message) {
        message.setText(message.getText() + "we do a lil impersonation :)");
        return message;
    }

}
