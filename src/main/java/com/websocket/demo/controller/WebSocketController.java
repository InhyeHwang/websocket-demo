package com.websocket.demo.controller;

import com.websocket.demo.model.HelloMessage;
import com.websocket.demo.model.ModelInfoHolder;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSocketController {

    private ObjectMapper mapper;
    private ModelInfoHolder modelInfoHolder;
    private SimpMessagingTemplate messageTemplate; // 메세지를 보내기 위한 객체

    public WebSocketController(ObjectMapper mapper, SimpMessagingTemplate messageTemplate, ModelInfoHolder modelInfoHolder) {
        this.mapper = mapper;
        this.messageTemplate = messageTemplate;
        this.modelInfoHolder = modelInfoHolder;
    }

    @Scheduled(fixedDelay = 1000)
    public void sendWebSocketUpdate() throws JsonProcessingException {
        modelInfoHolder.changeValues();
        this.messageTemplate.convertAndSend("/subscribe/values",
                mapper.writeValueAsString(modelInfoHolder.getModelInfoList()));
    }
}
