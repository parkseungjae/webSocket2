package com.chatproject.parkseungjae.Controller;

import com.chatproject.parkseungjae.Model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;

    //매핑경로가 /chat/message이지만 ChatConfig의 setApplicationDestinationPrefixes를 통해 /app을 줬기 때문에 실질경로는 /app/chat/message가 됨
    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        if(ChatMessage.MessageType.ENTER.equals(message.getType())){
            message.setMessage(message.getSender()+"님이 입장하였습니다");
        }
        sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(),message);
    }
}
