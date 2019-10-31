package com.example.topicservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TopicServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TopicServiceApplication.class, args);
    }

}
