package com.example.iotdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import com.hivemq.client.mqtt.datatypes.MqttQos;

import static com.hivemq.client.mqtt.MqttGlobalPublishFilter.ALL;
import static java.nio.charset.StandardCharsets.UTF_8;

@SpringBootApplication
public class IotDemoApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(IotDemoApplication.class, args);
        final String host = "12a52386eff742ef93a5765b44217ae7.s2.eu.hivemq.cloud";
        final String username = "haihoangnd";
        final String password = "Hh123456789";

        final Mqtt5BlockingClient client = MqttClient.builder()
                .useMqttVersion5()
                .serverHost(host)
                .serverPort(8884)
                .sslWithDefaultConfig()
                .webSocketConfig()
                .serverPath("mqtt")
                .applyWebSocketConfig()
                .buildBlocking();

        /**
         * Connect securely with username, password.
         */
        client.connectWith()
                .simpleAuth()
                .username(username)
                .password(UTF_8.encode(password))
                .applySimpleAuth()
                .send();

        System.out.println("Connected successfully");

        /**
         * Subscribe to the topic "my/test/topic" with qos = 2 and print the received message.
         */
        client.subscribeWith()
                .topicFilter("my/test/topic")
                .qos(MqttQos.EXACTLY_ONCE)
                .send();

        /**
         * Set a callback that is called when a message is received (using the async API style).
         * Then disconnect the client after a message was received.
         */
        client.toAsync().publishes(ALL, publish -> {
            System.out.println("Received message: " + publish.getTopic() + " -> " + UTF_8.decode(publish.getPayload().get()));

//            client.disconnect();
        });

        /**
         * Publish "Hello" to the topic "my/test/topic" with qos = 2.
         */
        client.publishWith()
                .topic("my/test/topic")
                .payload(UTF_8.encode("Hello"))
                .qos(MqttQos.EXACTLY_ONCE)
                .send();
    }
}
