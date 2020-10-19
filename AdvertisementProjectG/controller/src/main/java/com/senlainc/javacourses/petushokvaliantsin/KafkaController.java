package com.senlainc.javacourses.petushokvaliantsin;

import org.springframework.kafka.annotation.KafkaListener;

//@RestController
public class KafkaController {

    @KafkaListener(topics = "Hello", groupId = "app.id")
    public void test(String message) {
        System.err.println(message);
    }
}
