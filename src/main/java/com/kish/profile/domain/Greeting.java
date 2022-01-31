package com.kish.profile.domain;

import com.kish.profile.request.GreetingRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String language;
    private String message;

    public Greeting(GreetingRequest greetingRequest) {
        this.language = greetingRequest.getLanguage();
        this.message = greetingRequest.getMessage();
    }
}
