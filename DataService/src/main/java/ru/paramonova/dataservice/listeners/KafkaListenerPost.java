package ru.paramonova.dataservice.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import ru.paramonova.dataservice.dto.PostDto;
import ru.paramonova.dataservice.models.Post;
import ru.paramonova.dataservice.services.PostService;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaListenerPost {
    private final PostService postService;
    private final ObjectMapper objectMapper;

    @KafkaListener(concurrency = "2", topics = "post-topic", groupId = "post-group")
    private void addPost(String data) {
        try {
            PostDto postDto = objectMapper.readValue(data, PostDto.class);
            Optional<Post> postOptional = postService.addPost(postDto);
            if (postOptional.isPresent()) {
                System.out.println(postOptional.get());
            } else {
                log.info("Post не создан. Ошибка в данных DTO");
            }
        } catch (JsonProcessingException e) {
            log.error("Произошла JsonProcessingException");
        }
    }
}
