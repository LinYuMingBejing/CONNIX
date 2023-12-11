package com.cinnox.linebot.controller;

import com.cinnox.linebot.dto.request.EventWrapper;
import com.cinnox.linebot.service.UserMessageService;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
