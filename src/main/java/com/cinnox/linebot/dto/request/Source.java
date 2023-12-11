package com.cinnox.linebot.dto.request;

import lombok.Data;

@Data
public class Source {
    private String type;
    private String userId;
    private String groupId;
    private String roomId;
}
