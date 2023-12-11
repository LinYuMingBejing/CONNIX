package com.cinnox.linebot.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class EventWrapper {
    private String destination;
    private List<Event> events;
}
