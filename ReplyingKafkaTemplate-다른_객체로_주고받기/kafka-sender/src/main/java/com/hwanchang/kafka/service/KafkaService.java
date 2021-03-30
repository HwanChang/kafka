package com.hwanchang.kafka.service;

import com.hwanchang.kafka.message.RequestMessage;
import com.hwanchang.kafka.message.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaService {

    private final ReplyingKafkaTemplate<String, RequestMessage, ResponseMessage> replyingKafkaTemplate;

    public Optional<ResponseMessage> sendMessage(RequestMessage message) throws ExecutionException, InterruptedException {
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

}
