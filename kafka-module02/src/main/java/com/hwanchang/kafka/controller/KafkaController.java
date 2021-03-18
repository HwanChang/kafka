package com.hwanchang.kafka.controller;

import com.hwanchang.kafka.message.Message;
import com.hwanchang.kafka.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RequestMapping("api/v2")
@RequiredArgsConstructor
@RestController
public class KafkaController {

    private final KafkaService kafkaService;

    @PostMapping(path = "send")
    public Message send(@RequestBody Message message) throws ExecutionException, InterruptedException {
        return kafkaService.sendMessage(message).orElse(new Message("fail"));
    }

}
