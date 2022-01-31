package com.kish.profile.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kish.profile.domain.Greeting;
import com.kish.profile.repository.GreetingRepository;
import com.kish.profile.request.GreetingRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class KafkaConsumer {

    private final GreetingRepository greetingRepository;

    private final ObjectMapper objectMapper;

    public KafkaConsumer(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
        this.objectMapper = new ObjectMapper();
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group}")
    public void consume(@Payload List<GreetingRequest> greetingRequests){
        log.info("START: KafkaConsumer - consumer {} - {}", LocalDateTime.now(), greetingRequests.size());

        List<Greeting> greetingList = new ArrayList<>();
        for(GreetingRequest greetingRequest : greetingRequests){
            if(greetingRequest != null) {
                greetingList.add(new Greeting(greetingRequest));
            }
        }
        greetingRepository.saveAll(greetingList);

        List<Greeting> savedGreetings = greetingRepository.findAll();
        log.info("Saved Greetings: {}", savedGreetings);

        log.info("END: KafkaConsumer - consumer {}", LocalDateTime.now());

    }
}
