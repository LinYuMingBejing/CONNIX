package com.cinnox.linebot.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "message")
public class MessageEntity {
    @Id
    private String id;
    private String userId;
    private String message;

    public MessageEntity(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }
}
