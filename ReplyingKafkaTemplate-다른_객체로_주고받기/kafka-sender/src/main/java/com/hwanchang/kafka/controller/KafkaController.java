package com.hwanchang.kafka.controller;

import com.hwanchang.kafka.message.RequestMessage;
import com.hwanchang.kafka.message.ResponseMessage;
import com.hwanchang.kafka.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RequestMapping("api/v1")
@RequiredArgsConstructor
@RestController
public class KafkaController {

    private final KafkaService kafkaService;

    @PostMapping(path = "send")
    public ResponseMessage send(@RequestBody RequestMessage message) throws ExecutionException, InterruptedException {
        return kafkaService.sendMessage(message).orElse(new ResponseMessage("fail"));
    }

}
