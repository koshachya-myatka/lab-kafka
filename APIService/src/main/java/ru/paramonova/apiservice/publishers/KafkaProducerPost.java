package ru.paramonova.apiservice.publishers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.paramonova.apiservice.dto.PostDto;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerPost {
    @Value("${kafka.posts.topic}")
    private String postsTopic;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public boolean sendMessage(PostDto postDto) {
        try {
            String key = UUID.randomUUID().toString();
            String postDtoJson = objectMapper.writeValueAsString(postDto);
            kafkaTemplate.send(postsTopic, key, postDtoJson);
        } catch (JsonProcessingException e) {
            log.error("Произошла JsonProcessingException");
            return false;
        }
        return true;
    }
}
