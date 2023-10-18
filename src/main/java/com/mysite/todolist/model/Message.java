package com.mysite.todolist.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    
    String text;
    String senderUsername;

    @Override
    public String toString() {
        return senderUsername + ": " + text;
    }
}
