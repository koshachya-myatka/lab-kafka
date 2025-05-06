package ru.paramonova.dataservice.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.paramonova.dataservice.dto.CommentDto;
import ru.paramonova.dataservice.models.Comment;
import ru.paramonova.dataservice.services.CommentService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class KafkaListenerComment {
    private final CommentService commentService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "comment_topic")
    private void addComment(String data) {
        try {
            CommentDto commentDto = objectMapper.readValue(data, CommentDto.class);
            Optional<Comment> commentOptional = commentService.addComment(commentDto);
            System.out.println(commentOptional.get());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
