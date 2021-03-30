package com.hwanchang.kafka.config;

import com.hwanchang.kafka.message.RequestMessage;
import com.hwanchang.kafka.message.ResponseMessage;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaConfig {

    @Bean
    public ReplyingKafkaTemplate<String, RequestMessage, ResponseMessage> replyingKafkaTemplate(
            ProducerFactory<String, RequestMessage> pf,
            ConcurrentKafkaListenerContainerFactory<String, ResponseMessage> factory
    ) {
        ConcurrentMessageListenerContainer<String, ResponseMessage> replyContainer = factory.createContainer("v1.kafka.reply");
        replyContainer.getContainerProperties().setMissingTopicsFatal(false);
        return new ReplyingKafkaTemplate<>(pf, replyContainer);
    }

    @Bean
    public KafkaTemplate<String, ResponseMessage> replyTemplate(
            ProducerFactory<String, ResponseMessage> pf,
            ConcurrentKafkaListenerContainerFactory<String, ResponseMessage> factory
    ) {
        KafkaTemplate<String, ResponseMessage> kafkaTemplate = new KafkaTemplate<>(pf);
        factory.getContainerProperties().setMissingTopicsFatal(false);
        factory.setReplyTemplate(kafkaTemplate);
        return kafkaTemplate;
    }

    @Bean
    public NewTopic kafkaMessage() {
        return TopicBuilder.name("v1.kafka.message")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic kafkaReply() {
        return TopicBuilder.name("v1.kafka.reply")
                .partitions(1)
                .replicas(1)
                .build();
    }

}
