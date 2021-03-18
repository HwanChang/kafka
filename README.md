# kafka
kafka 사용법 정리

1. ReplyingKafkaTemplate 활용하여 모듈간 message 연동

    - kafka 기본 설정 세팅
        - [Module01 설정](https://github.com/HwanChang/kafka/blob/main/kafka-module01/src/main/resources/application.yml)
        - [Module02 설정](https://github.com/HwanChang/kafka/blob/main/kafka-module02/src/main/resources/application.yml)
        
    - ReplyingKafkaTemplate Bean 등록
        - [Module01 Bean 등록](https://github.com/HwanChang/kafka/blob/main/kafka-module01/src/main/java/com/hwanchang/kafka/config/KafkaConfig.java)
        - [Module02 Bean 등록](https://github.com/HwanChang/kafka/blob/main/kafka-module02/src/main/java/com/hwanchang/kafka/config/KafkaConfig.java)

    - 메시징 처리 구현
        - [Module01 메시징 처리](https://github.com/HwanChang/kafka/blob/main/kafka-module01/src/main/java/com/hwanchang/kafka/service/KafkaService.java)
        - [Module02 메시징 처리](https://github.com/HwanChang/kafka/blob/main/kafka-module02/src/main/java/com/hwanchang/kafka/service/KafkaService.java)
        
    - 메시지 전송 요청
        ```json
            ### Module01 요청
            POST http://localhost:8081/api/v1/send
            Content-Type: application/json

            {
              "message": "v1 테스트"
            }

            ### Module02 요청
            POST http://localhost:8082/api/v2/send
            Content-Type: application/json

            {
              "message": "v2 테스트"
            }
        ```
