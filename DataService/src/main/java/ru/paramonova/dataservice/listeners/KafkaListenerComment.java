package ru.paramonova.dataservice.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.paramonova.dataservice.dto.CommentDto;
import ru.paramonova.dataservice.models.Comment;
import ru.paramonova.dataservice.services.CommentService;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaListenerComment {
    private final CommentService commentService;
    private final ObjectMapper objectMapper;

    @KafkaListener(concurrency = "2", topics = "comment-topic", groupId = "comment-group")
    private void addComment(String data) {
        try {
            CommentDto commentDto = objectMapper.readValue(data, CommentDto.class);
            Optional<Comment> commentOptional = commentService.addComment(commentDto);
            if (commentOptional.isPresent()) {
                System.out.println(commentOptional.get());
            } else {
                log.info("Comment не создан. Ошибка в данных DTO");
            }
        } catch (JsonProcessingException e) {
            log.error("Произошла JsonProcessingException");
        }
    }
}
