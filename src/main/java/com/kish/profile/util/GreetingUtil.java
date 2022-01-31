package com.kish.profile.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kish.profile.request.GreetingRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GreetingUtil {

    public static GreetingRequest convertToGreetingRequest(String greetingString, ObjectMapper objectMapper){

        GreetingRequest greetingRequest = null;
        try {
            greetingRequest = new ObjectMapper().readValue(greetingString, GreetingRequest.class);
        } catch (Exception ex) {
            log.error("GreetingUtil - convertToGreetingRequest : Exception - Message: {}", ex.getMessage());
        }

        return greetingRequest;
    }

}
