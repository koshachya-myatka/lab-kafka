package ru.paramonova.apiservice.configurations;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {
    @Value("${kafka.posts.topic}")
    private String postsTopic;
    @Value("${kafka.comments.topic}")
    private String commentsTopic;

    @Bean
    public NewTopic createPostsTopic() {
        return TopicBuilder.name(postsTopic).partitions(2).build();
    }

    @Bean
    public NewTopic createCommentsTopic() {
        return TopicBuilder.name(commentsTopic).partitions(4).build();
    }
}
