package ru.paramonova.dataservice.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.paramonova.dataservice.dto.PostDto;
import ru.paramonova.dataservice.models.Post;
import ru.paramonova.dataservice.services.PostService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class KafkaListenerPost {
//    @Value("${kafka.posts.topic}")
//    private final String topic;
    private final PostService postService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "post-topic", groupId = "post-group")
    private void addPost(String data) {
        try {
            PostDto postDto = objectMapper.readValue(data, PostDto.class);
            Optional<Post> postOptional = postService.addPost(postDto);
            System.out.println(postOptional.get());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
