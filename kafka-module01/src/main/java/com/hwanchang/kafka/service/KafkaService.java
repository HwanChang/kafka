package com.hwanchang.kafka.service;

import com.hwanchang.kafka.message.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaService {

    private final ReplyingKafkaTemplate<String, Message, Message> replyingKafkaTemplate;

    public Optional<Message> sendMessage(Message message) throws ExecutionException, InterruptedException {
        return Optional.ofNullable(replyingKafkaTemplate.sendAndReceive(new ProducerRecord<>("v1.kafka.message", message))
                .completable()
                .exceptionally(throwable -> {
                    log.warn("fail: {}", throwable.getMessage(), throwable);
                    return null;
                })
                .thenApply(ConsumerRecord::value)
                .get()
        );
    }

    @KafkaListener(topics = "v2.kafka.message", groupId = "kafka-module")
    @SendTo
    public Message listen(Message message) {
        log.info(message.getMessage());
        return new Message(String.format("%s success..", message.getMessage()));
    }

}
