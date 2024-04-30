package com.ewanthau.librarysystem.util;

import com.ewanthau.librarysystem.dto.BookAvailableMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebSocketClient {

    private final SimpMessagingTemplate template;

    public void sendMessage(BookAvailableMessage message) {
        try {
            template.convertAndSend("/topic/availableBooks", message);
        } catch (Exception e) {
            log.error("Failed to send book available message: {}", e.getMessage());
            log.debug(Arrays.toString(e.getStackTrace()));
        }
    }
}
