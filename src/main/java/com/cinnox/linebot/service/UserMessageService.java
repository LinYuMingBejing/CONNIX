package com.cinnox.linebot.service;

import com.cinnox.linebot.dto.request.Event;
import com.cinnox.linebot.dto.request.EventWrapper;
import com.cinnox.linebot.entity.MessageEntity;
import com.cinnox.linebot.entity.UserEntity;
import com.cinnox.linebot.repository.MessageRepository;

import com.cinnox.linebot.repository.UserRepository;
import com.linecorp.bot.client.LineMessagingClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    LineMessagingClient lineMessagingClient;

    public void addUserInfo(EventWrapper eventWrapper){
        Event event = eventWrapper.getEvents()
                .stream()
                .findFirst()
                .orElse(null);

        String userId = event.getSource().getUserId();

        lineMessagingClient.getProfile(userId)
                .whenComplete((profile, throwable) -> {
                    userRepository.save(new UserEntity(userId, profile.getDisplayName(), event.getReplyToken()));
                    messageRepository.save(new MessageEntity(userId, event.getMessage().getText()));
                });
    }
}
