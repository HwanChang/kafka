# kafka
kafka 사용법 정리

1. ReplyingKafkaTemplate 활용하여 모듈간 message 연동 (Request, Response 각각 다른 객체 사용)

    - kafka 기본 설정 세팅
        - [Sender 설정](https://github.com/HwanChang/kafka/blob/main/ReplyingKafkaTemplate-다른_객체로_주고받기/kafka-sender/src/main/resources/application.yml)
        - [Receiver 설정](https://github.com/HwanChang/kafka/blob/main/ReplyingKafkaTemplate-다른_객체로_주고받기/kafka-receiver/src/main/resources/application.yml)
        
    - ReplyingKafkaTemplate Bean 등록
        - [Sender Bean 등록](https://github.com/HwanChang/kafka/blob/main/ReplyingKafkaTemplate-다른_객체로_주고받기/kafka-sender/src/main/java/com/hwanchang/kafka/config/KafkaConfig.java)

    - 메시징 처리 구현
        - [Sender 메시징 처리](https://github.com/HwanChang/kafka/blob/main/ReplyingKafkaTemplate-다른_객체로_주고받기/kafka-sender/src/main/java/com/hwanchang/kafka/service/KafkaService.java)
        - [Receiver 메시징 처리](https://github.com/HwanChang/kafka/blob/main/ReplyingKafkaTemplate-다른_객체로_주고받기/kafka-receiver/src/main/java/com/hwanchang/kafka/service/KafkaService.java)
        
    - 메시지 전송 요청
        ```json
            ### Module01 요청
            POST http://localhost:8081/api/v1/send
            Content-Type: application/json

            {
              "message": "v1 테스트"
            }
        ```
