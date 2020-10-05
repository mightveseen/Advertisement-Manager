package com.senlainc.javacourses.petushokvaliantsin;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @KafkaListener(topics = "Hello", groupId = "app.id")
    public void test(String message) {
        System.err.println(message);
    }
}
