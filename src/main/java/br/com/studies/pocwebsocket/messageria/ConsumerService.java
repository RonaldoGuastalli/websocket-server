package br.com.studies.pocwebsocket.messageria;


import br.com.studies.pocwebsocket.handler.SocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;

import java.util.Arrays;
import java.util.Properties;

@Component
@Slf4j
public class ConsumerService {

    @Autowired
    private SocketHandler socketHandler;

    @EventListener(ApplicationReadyEvent.class)
    public void consumer() throws IOException{
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "false");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("first_topic"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                this.returnMessageToClient(record.value());
                log.info("mensagem enviada para o client!");
            }
        }


    }

    public void returnMessageToClient(String message) throws IOException {
        socketHandler.returnMessageToClient(message);
    }
}
