package com.example.iotdemo.service;

import com.example.iotdemo.config.MqttClientWrapper;
import com.example.iotdemo.entity.TreeStatus;
import com.example.iotdemo.response.GeneralResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class MqttService {

    private final MqttClientWrapper mqttClientWrapper;
    private final RestTemplate restTemplate;

    @Autowired
    public MqttService(MqttClientWrapper mqttClientWrapper, RestTemplate restTemplate) {
        this.mqttClientWrapper = mqttClientWrapper;
        this.restTemplate = restTemplate;

        // Subscribe to the topic "treeStatus"
        this.mqttClientWrapper.subscribe("treeStatus");

        // Set callback for message reception
        this.mqttClientWrapper.setCallback(publish -> {
            System.out.println("Received message: " +
                    publish.getTopic() + " -> " +
                    UTF_8.decode(publish.getPayload().get()));

            // Chuyển đổi payload JSON thành đối tượng TreeStatus
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                TreeStatus treeStatus = objectMapper.readValue(
                        UTF_8.decode(publish.getPayload().get()).toString(),
                        TreeStatus.class);

                // Gọi API để tạo mới TreeStatus
                ResponseEntity<GeneralResponse> responseEntity = restTemplate.postForEntity(
                        "http://localhost:8080/api/treeStatus", treeStatus, GeneralResponse.class);

                // Log kết quả từ API response
                System.out.println("API Response: " + responseEntity.getBody());
            } catch (JsonProcessingException e) {
                // Xử lý lỗi khi chuyển đổi JSON
                e.printStackTrace();
            }
        });
    }
}