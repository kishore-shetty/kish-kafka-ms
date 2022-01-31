package com.kish.profile.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("kafka")
public class KafkaProperties {

    private String bootstrapServers;
    private int concurrency;
    private int maxBatchSize;
    private String topic;
    private String groupId;
}
