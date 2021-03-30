package com.hwanchang.kafka.service;

import com.hwanchang.kafka.message.RequestMessage;
import com.hwanchang.kafka.message.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaService {

    @KafkaListener(topics = "v1.kafka.message", groupId = "kafka-module")
    @SendTo
    public ResponseMessage listen(RequestMessage message) {
        log.info(message.getMessage());
        return new ResponseMessage(String.format("%s success..", message.getMessage()));
    }

}
