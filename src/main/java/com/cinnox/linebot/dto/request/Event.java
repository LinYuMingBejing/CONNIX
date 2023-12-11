package com.cinnox.linebot.dto.request;

import lombok.Data;

@Data
public class Event {
    private String replyToken;
    private String mode;
    private String type;
    private Source source;
    private String timestamp;
    private Message message;
}
