package com.cinnox.linebot.controller;

import com.cinnox.linebot.dto.request.EventWrapper;
import com.cinnox.linebot.dto.request.ReplyText;
import com.cinnox.linebot.dto.response.Response;
import com.cinnox.linebot.exception.UserNotFoundException;
import com.cinnox.linebot.service.UserMessageService;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@LineMessageHandler
public class LineWebhookController {
    @Autowired
    UserMessageService userMessageService;

    @Autowired
    LineMessagingClient lineMessagingClient;

    @PostMapping("/webhook")
    public void saveMessage(@RequestBody EventWrapper eventWrapper) {
        userMessageService.addUserInfo(eventWrapper);
    }

    @PostMapping("/message")
    public Response replyMessage(@RequestBody ReplyText replyText) {
        try{
            userMessageService.reply(replyText);
            Response response = Response.success();
            return response;
        } catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
