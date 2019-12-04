package com.websocket.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 서버에 접속 할 endpoint 설정
        registry.addEndpoint("/ws").withSockJS();
    }

    // MessageBroker 설정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app"); // client가 보내준 SEND 요청을 처리
        // 해당 경로로 SimpleBroker를 등록한다.
        // SimpleBroker는 해당하는 경로를 SUBSCRIBE 하는 client에게 메세지를 전달한다.
        config.enableSimpleBroker("/subscribe");

        /*// SimpleBroker의 기능과 외부 message broker(RabbitMQ, ActiveMQ등)에 메시지를 전달하는 기능을 가지고 있다.
        config.enableStompBrokerRelay("");*/

    }
}


