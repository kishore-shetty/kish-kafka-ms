package com.kish.profile;

import com.kish.profile.request.GreetingRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Scanner;

@SpringBootApplication
public class ProfileApplication {

	private static KafkaTemplate<String, GreetingRequest> kafkaTemplate;

	public ProfileApplication(KafkaTemplate<String, GreetingRequest> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProfileApplication.class, args);

		boolean continueFlag = true;
		int i = 0;
		Scanner scanner= new Scanner(System.in);
		while (continueFlag){

			GreetingRequest greetingRequest = new GreetingRequest();
			greetingRequest.setLanguage("English-" + i);
			greetingRequest.setMessage("Good Morning-" + i);
			kafkaTemplate.send("kishore.topic", greetingRequest);

			i++;
			System.out.println("Do you want to publish more message to kafka: ");
			continueFlag = scanner.nextBoolean();
		}
	}


}
