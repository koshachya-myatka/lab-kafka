package ru.paramonova.apiservice.publishers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.paramonova.apiservice.dto.CommentDto;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerComment {
    @Value("${kafka.comments.topic}")
    private String commentsTopic;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public boolean sendMessage(CommentDto commentDto) {
        try {
            String key = UUID.randomUUID().toString();
            String commentDtoJson = objectMapper.writeValueAsString(commentDto);
            kafkaTemplate.send(commentsTopic, key, commentDtoJson);
        } catch (JsonProcessingException e) {
            log.error("Произошла JsonProcessingException");
            return false;
        }
        return true;
    }
}
