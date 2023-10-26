package com.chatproject.parkseungjae.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class ChatConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        // cliend에서 websocket연결할때 사용할 API 경로를 설정해주는 메소드.
        registry.addEndpoint("/ws/chat").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){

        // 메세지 받을때 관련 경로 설정
        // "/queue", "/topic" 이 두경로가 prefix(api 경로 맨 앞)에 붙은 경우, messageBroker가 잡아서 해당 채팅방을 구독하고 있는 클라이언트에게 메세지를 전달
        // 주로 "/queue"는 1대1, "/topic"은 1대N 메세징일때 주로 사용함.
        registry.enableSimpleBroker("/queue","/topic");

        // 메세지 보낼때 관련 경로 설정
        registry.setApplicationDestinationPrefixes("/app");
    }


}
