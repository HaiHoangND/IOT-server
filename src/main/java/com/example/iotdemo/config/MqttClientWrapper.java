package com.example.iotdemo.config;

import com.example.iotdemo.entity.TreeStatus;
import com.example.iotdemo.response.GeneralResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.function.Consumer;
import static com.hivemq.client.mqtt.MqttGlobalPublishFilter.ALL;
import static java.nio.charset.StandardCharsets.UTF_8;

public class MqttClientWrapper {
    private final Mqtt5BlockingClient client;

    public MqttClientWrapper(String host, int port, String username, String password) {
        this.client = MqttClient.builder()
                .useMqttVersion5()
                .serverHost(host)
                .serverPort(port)
                .sslWithDefaultConfig()
                .buildBlocking();

        // connect to HiveMQ Cloud with TLS and username/password
        client.connectWith()
                .simpleAuth()
                .username(username)
                .password(UTF_8.encode(password))
                .applySimpleAuth()
                .send();
        System.out.println("Connected successfully");
    }

    public void subscribe(String topic) {
        // subscribe to the specified topic
        client.subscribeWith()
                .topicFilter(topic)
                .send();
    }

    public void setCallback(Consumer<Mqtt5Publish> callback) {
        // set a callback that is called when a message is received (using the async API style)
        client.toAsync().publishes(ALL, callback);
    }

    public void publish(String topic, String message) {
        // publish a message to the specified topic
        client.publishWith()
                .topic(topic)
                .payload(UTF_8.encode(message))
                .send();
    }

    public void disconnect() {
        // disconnect the MQTT client
        client.disconnect();
    }
}