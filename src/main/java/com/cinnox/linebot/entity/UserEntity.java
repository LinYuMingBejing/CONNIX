package com.cinnox.linebot.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class UserEntity {
    @Id
    private String userId;
    private String displayName;
    private String replyToken;

    public UserEntity(String userId, String displayName, String replyToken) {
        this.userId = userId;
        this.displayName = displayName;
        this.replyToken = replyToken;
    }
}
