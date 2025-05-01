package ru.paramonova.dataservice.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.paramonova.dataservice.dto.PostDto;
import ru.paramonova.dataservice.services.PostService;

@Component
@RequiredArgsConstructor
public class PostListener {
    private final PostService postService;
    private final ObjectMapper objectMapper;

    //todo доделать
    @KafkaListener
    private void addPost(String message){
        PostDto postDto = new PostDto();
        if (postService.addPost(postDto).isEmpty()) {

        }
    }
}
