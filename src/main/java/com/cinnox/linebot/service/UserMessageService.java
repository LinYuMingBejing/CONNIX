package com.cinnox.linebot.service;

import com.cinnox.linebot.dto.request.Event;
import com.cinnox.linebot.dto.request.EventWrapper;
import com.cinnox.linebot.dto.request.ReplyText;
import com.cinnox.linebot.entity.MessageEntity;
import com.cinnox.linebot.entity.UserEntity;
import com.cinnox.linebot.exception.UserNotFoundException;
import com.cinnox.linebot.repository.MessageRepository;

import com.cinnox.linebot.repository.UserRepository;
import com.linecorp.bot.client.LineMessagingClient;

import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public void reply(ReplyText replyText) throws UserNotFoundException {
        try{
            String replyToken = this.findReplyTokenByUserId(replyText.getUserId());
            lineMessagingClient.replyMessage(new ReplyMessage(replyToken, new TextMessage(replyText.getText())));
        } catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }
    }

    public String findReplyTokenByUserId(String userId) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        return userEntity.getReplyToken();
    }

    public List<String> findMessageByUserId(String userId) throws UserNotFoundException {
        List<String> messages = messageRepository.findByUserId(userId)
                .stream()
                .map(entity -> entity.getMessage())
                .collect(Collectors.toList());

        if (messages.isEmpty()){
            throw new UserNotFoundException();
        }
        return messages;
    }
}
