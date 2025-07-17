
//package websocketgateway.kafka;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class MessageProducer {
//
//    @Autowired
//    private KafkaTemplate<String, Object> kafkaTemplate;
//
//    public void send(String topic, Object payload) {
//        kafkaTemplate.send(topic, payload);
//    }
//}
package websocketgateway.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topic, Object payload) {
        logger.info("➡️ Producing message to Kafka topic [{}]: {}", topic, payload);
        kafkaTemplate.send(topic, payload);
    }
<<<<<<< HEAD
=======


>>>>>>> 6a1d08851ff8a3e2ea7a9353b54c701c7a204385
}
