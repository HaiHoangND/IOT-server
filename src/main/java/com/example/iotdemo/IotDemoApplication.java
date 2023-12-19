package com.example.iotdemo;

import com.example.iotdemo.config.MqttClientWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import static java.nio.charset.StandardCharsets.UTF_8;

@SpringBootApplication
public class IotDemoApplication {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public MqttClientWrapper mqttClientWrapper() {
        final String host = "12a52386eff742ef93a5765b44217ae7.s2.eu.hivemq.cloud";
        final String username = "haihoangnd";
        final String password = "Hh123456789";

        return new MqttClientWrapper(host, 8883, username, password);
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(IotDemoApplication.class, args);

//        final Mqtt5BlockingClient client = MqttClient.builder()
//                .useMqttVersion5()
//                .serverHost(host)
//                .serverPort(8883)
//                .sslWithDefaultConfig()
//                .buildBlocking();
//
//        // connect to HiveMQ Cloud with TLS and username/pw
//        client.connectWith()
//                .simpleAuth()
//                .username(username)
//                .password(UTF_8.encode(password))
//                .applySimpleAuth()
//                .send();
//
//        System.out.println("Connected successfully");
//
//        // subscribe to the topic "my/test/topic"
//        client.subscribeWith()
//                .topicFilter("treeStatus")
//                .send();
//
//        // set a callback that is called when a message is received (using the async API style)
//        client.toAsync().publishes(ALL, publish -> {
//            System.out.println("Received message: " +
//                    publish.getTopic() + " -> " +
//                    UTF_8.decode(publish.getPayload().get()));
//
//            // disconnect the client after a message was received
////            client.disconnect();
//        });
//
//        // publish a message to the topic "my/test/topic"
//        client.publishWith()
//                .topic("my/test/topic")
//                .payload(UTF_8.encode("Hello"))
//                .send();
//        MqttClientWrapper mqttClientWrapper = new MqttClientWrapper(host, 8883, username, password);
//
        // Subscribe to the topic "treeStatus"
//        mqttClientWrapper.subscribe("treeStatus");
//
//        // Set callback for message reception
//        mqttClientWrapper.setCallback(publish -> {
//            System.out.println("Received message: " +
//                    publish.getTopic() + " -> " +
//                    UTF_8.decode(publish.getPayload().get()));
//        });
//
//        // Publish a message to the topic "my/test/topic"
//        mqttClientWrapper.publish("my/test/topic", "Hello");
    }
}
